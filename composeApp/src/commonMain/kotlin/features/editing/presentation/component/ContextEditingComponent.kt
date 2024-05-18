package features.editing.presentation.component

import com.arkivanov.decompose.ComponentContext
import core.components.BaseCoroutineComponent
import core.components.SnackBarComponent
import core.components.StateComponent
import core.components.TextInputComponent
import core.utils.text_validators.AlwaysValidValidator
import core.utils.text_validators.LengthValidator
import features.contexts.domain.use_case.ContextUseCase
import features.editing.presentation.model.EditingScreenState
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ContextEditingComponent(
    componentContext: ComponentContext,
    private val id: String?,
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

    fun onSavingTypeChange(index: Int) {}

    fun onSaveClicked() {}

    private fun getContext() = componentScope.launch {
        if (id == null) {
            return@launch
        }

        stateComponent.reduce { copy(isLoading = true) }

        contextUseCase.getRich(id).fold(
            onSuccess = {
                nameComponent.onChanged(it.name)
                descriptionComponent.onChanged(it.description)
                contextComponent.onChanged(it.context)
            },
            onFailure = {
                snackBarComponent.showError(it.message)
            }
        )

        stateComponent.reduce { copy(isLoading = false) }
    }
}
