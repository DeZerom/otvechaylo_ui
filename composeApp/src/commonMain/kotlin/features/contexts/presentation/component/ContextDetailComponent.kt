package features.contexts.presentation.component

import app.config.AppConfig
import app.config.CURRENT_CONFIG
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnDestroy
import core.components.BaseCoroutineComponent
import core.components.SnackBarComponent
import core.components.StateComponent
import core.components.TextInputComponent
import core.utils.text_validators.LengthValidator
import features.contexts.domain.model.ContextLightweight
import features.contexts.domain.model.ContextRich
import features.contexts.domain.model.ContextSource
import features.contexts.domain.use_case.AnsweringUseCase
import features.contexts.domain.use_case.ContextUseCase
import features.contexts.presentation.model.AnswerState
import features.contexts.presentation.model.ContextDetailScreenState
import features.editing.presentation.args.ContextEditingArgs
import features.editing.presentation.callback.ContextChangePayload
import features.editing.presentation.callback.ContextChangedListener
import features.editing.presentation.callback.ContextChangedListenersHolder
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.getString
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import otvechayloui.composeapp.generated.resources.Res
import otvechayloui.composeapp.generated.resources.fill_fields
import otvechayloui.composeapp.generated.resources.unknown_error

class ContextDetailComponent(
    componentContext: ComponentContext,
    private val onBackPressed: () -> Unit,
    private val onEditPressed: (ContextEditingArgs) -> Unit
): BaseCoroutineComponent(componentContext), KoinComponent, ContextChangedListener {
    val questionComponent = TextInputComponent(LengthValidator.notEmpty())
    val stateComponent = StateComponent(ContextDetailScreenState())
    val snackBarComponent = SnackBarComponent()

    private val contextUseCase: ContextUseCase by inject()
    private val answeringUseCase: AnsweringUseCase by inject()

    private var hasConflict = false
    private var source = ContextSource.NETWORK
    private val hasNetworkSource get() = source == ContextSource.NETWORK || source == ContextSource.BOTH

    init {
        ContextChangedListenersHolder.register(this)
        doOnDestroy {
            ContextChangedListenersHolder.unregister(this)
        }
    }

    override fun onAdded(context: ContextRich) = Unit

    override fun onChanged(id: String?, payload: ContextChangePayload) {
        if (stateComponent.state.value.id != id) return

        stateComponent.reduce {
            copy(
                name = payload.name,
                description = payload.description,
                context = payload.context,
                isFullyLoaded = true
            )
        }
    }

    fun setContext(context: ContextLightweight) {
        source = context.source
        hasConflict = context.hasConflict

        stateComponent.setState(
            ContextDetailScreenState(
                id = context.id,
                name = context.name,
                description = context.description
            )
        )
    }

    fun onBackPressed() {
        when (CURRENT_CONFIG) {
            AppConfig.DESKTOP -> stateComponent.setState(ContextDetailScreenState())
            AppConfig.MOBILE -> onBackPressed.invoke()
        }
    }

    fun onShowHideClicked() {
        if (stateComponent.state.value.isShown) {
            stateComponent.reduce { copy(isShown = false) }
        } else {
            showContext()
        }
    }

    fun onEditPressed() {
        onEditPressed(
            ContextEditingArgs(
                id = stateComponent.state.value.id,
                source = source,
                hasConflict = hasConflict
            )
        )
    }

    @OptIn(ExperimentalResourceApi::class)
    fun getAnswer() = componentScope.launch {
        if (!questionComponent.validate()) {
            snackBarComponent.showError(getString(Res.string.fill_fields))
            return@launch
        }

        stateComponent.reduce {
            copy(
                isSendQuestionButtonVisible = false,
                answerState = AnswerState.Loading
            )
        }

        val answerRes = if (hasNetworkSource) {
            answeringUseCase.getAnswerById(
                id = stateComponent.state.value.id,
                question = questionComponent.value.value
            )
        } else {
            answeringUseCase.getAnswerByContext(
                context = stateComponent.state.value.context,
                question = questionComponent.value.value
            )
        }

        val unknownError = getString(Res.string.unknown_error)
        stateComponent.reduce {
            copy(
                isSendQuestionButtonVisible = true,
                answerState = answerRes.fold(
                    onSuccess = {
                        if (it.isNotBlank()) {
                            AnswerState.Answer(it)
                        } else {
                            AnswerState.NotFound
                        }
                    },
                    onFailure = {
                        AnswerState.Error(it.message ?: unknownError)
                    }
                )
            )
        }
    }

    private fun showContext() {
        if (stateComponent.state.value.isFullyLoaded) {
            stateComponent.reduce { copy(isShown = true) }
        } else {
            downloadRichContext()
        }
    }

    private fun downloadRichContext() = componentScope.launch {
        stateComponent.reduce { copy(isContextLoading = true) }

        contextUseCase.getRich(
            id = stateComponent.state.value.id,
            source = source,
            hasConflict = hasConflict
        ).fold(
            onSuccess = {
                stateComponent.reduce {
                    copy(
                        isContextLoading = false,
                        isShown = true,
                        context = it.context,
                        isFullyLoaded = true
                    )
                }
            },
            onFailure = {
                stateComponent.reduce { copy(isContextLoading = false) }
                snackBarComponent.showError(it.message)
            }
        )
    }
}