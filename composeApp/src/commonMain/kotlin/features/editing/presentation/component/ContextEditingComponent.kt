package features.editing.presentation.component

import com.arkivanov.decompose.ComponentContext
import core.components.BaseCoroutineComponent
import core.components.SnackBarComponent
import core.components.StateComponent
import core.components.TextInputComponent
import core.utils.text_res.TextResource.Companion.TextResource
import core.utils.text_validators.AlwaysValidValidator
import core.utils.text_validators.LengthValidator
import features.contexts.domain.model.ContextSource
import features.contexts.domain.use_case.ContextUseCase
import features.editing.presentation.args.ContextEditingArgs
import features.editing.presentation.callback.ContextChangePayload
import features.editing.presentation.callback.ContextChangedListenersHolder
import features.editing.presentation.model.EditingScreenState
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import otvechayloui.composeapp.generated.resources.Res
import otvechayloui.composeapp.generated.resources.fill_fields
import otvechayloui.composeapp.generated.resources.saved_successfully
import otvechayloui.composeapp.generated.resources.unknown_error

class ContextEditingComponent(
    componentContext: ComponentContext,
    private val args: ContextEditingArgs?,
    private val onBackPressed: () -> Unit
): BaseCoroutineComponent(componentContext), KoinComponent {
    val nameComponent = TextInputComponent(LengthValidator.notEmpty())
    val descriptionComponent = TextInputComponent(AlwaysValidValidator())
    val contextComponent = TextInputComponent(LengthValidator.notEmpty())

    val snackBarComponent = SnackBarComponent()
    val stateComponent = StateComponent(EditingScreenState())

    private val contextUseCase: ContextUseCase by inject()

    init {
        getContext()
    }

    fun onBackPressed() {
        onBackPressed.invoke()
    }

    fun onSavingTypeChange(index: Int) {
        stateComponent.reduce { copy(selectedIndex = index) }
    }

    @OptIn(ExperimentalResourceApi::class)
    fun onSaveClicked() = componentScope.launch {
        if (!validate()) {
            snackBarComponent.showError(TextResource(Res.string.fill_fields))
            return@launch
        }

        val onlyLocally = if (stateComponent.state.value.isSavingTypeChooseVisible)
            stateComponent.state.value.selectedIndex == 0
        else
            false

        stateComponent.reduce { copy(isSaving = true) }

        if (args?.id != null) {
            contextUseCase.saveChanges(
                id = args.id,
                onlyLocally = onlyLocally,
                name = nameComponent.value.value,
                description = descriptionComponent.value.value,
                context = contextComponent.value.value
            ).fold(
                onSuccess = {
                    snackBarComponent.showSuccess(TextResource(Res.string.saved_successfully))
                    ContextChangedListenersHolder.onContextChanged(
                        id = args.id,
                        payload = ContextChangePayload(
                            name = nameComponent.value.value,
                            description = descriptionComponent.value.value,
                            context = contextComponent.value.value,
                            source = if (onlyLocally) ContextSource.DB else ContextSource.BOTH
                        )
                    )
                },
                onFailure = {
                    snackBarComponent.showError(TextResource(Res.string.unknown_error))
                }
            )
        } else {
            contextUseCase.saveContext(
                onlyLocally = onlyLocally,
                name = nameComponent.value.value,
                description = descriptionComponent.value.value,
                context = contextComponent.value.value
            ).fold(
                onSuccess = {
                    snackBarComponent.showSuccess(TextResource(Res.string.saved_successfully))
                    ContextChangedListenersHolder.onContextAdded(it)
                },
                onFailure = {
                    snackBarComponent.showError(TextResource(Res.string.unknown_error))
                }
            )
        }

        stateComponent.reduce {
            copy(
                isSaving = false,
                isSavingTypeChooseVisible = onlyLocally
            )
        }
    }

    private fun validate() = nameComponent.validate()
            && descriptionComponent.validate()
            && contextComponent.validate()

    private fun getContext() = componentScope.launch {
        if (args?.id == null) {
            return@launch
        }

        stateComponent.reduce { copy(isLoading = true) }

        contextUseCase.getRich(
            id = args.id,
            source = args.source,
            hasConflict = args.hasConflict
        ).fold(
            onSuccess = {
                nameComponent.onChanged(it.name)
                descriptionComponent.onChanged(it.description)
                contextComponent.onChanged(it.context)

                stateComponent.reduce {
                    copy(
                        isSavingTypeChooseVisible = it.source == ContextSource.DB
                    )
                }
            },
            onFailure = {
                snackBarComponent.showError(it.message)
            }
        )

        stateComponent.reduce { copy(isLoading = false) }
    }
}
