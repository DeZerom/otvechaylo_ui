package features.contexts.presentation.component

import app.config.AppConfig
import app.config.CURRENT_CONFIG
import com.arkivanov.decompose.ComponentContext
import core.components.BaseCoroutineComponent
import core.components.SnackBarComponent
import core.components.StateComponent
import core.components.TextInputComponent
import core.utils.text_validators.LengthValidator
import features.contexts.domain.model.ContextLightweight
import features.contexts.domain.model.ContextSource
import features.contexts.domain.use_case.AnsweringUseCase
import features.contexts.domain.use_case.ContextUseCase
import features.contexts.presentation.model.AnswerState
import features.contexts.presentation.model.ContextDetailScreenState
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
    private val onBackPressed: () -> Unit
): BaseCoroutineComponent(componentContext), KoinComponent {
    val questionComponent = TextInputComponent(LengthValidator.notEmpty())
    val stateComponent = StateComponent(ContextDetailScreenState())
    val snackBarComponent = SnackBarComponent()

    private val contextUseCase: ContextUseCase by inject()
    private val answeringUseCase: AnsweringUseCase by inject()

    private var hasNetworkSource = false

    fun setContext(context: ContextLightweight) {
        hasNetworkSource = context.source == ContextSource.NETWORK || context.source == ContextSource.DB

        stateComponent.setState(
            ContextDetailScreenState(
                id = context.id,
                name = context.name,
                description = context.description,
                isFullyLoaded = context.source == ContextSource.DB || context.source == ContextSource.BOTH
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
            hasNetworkSource = false
        } else {
            showContext()
        }
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

        contextUseCase.getRich(stateComponent.state.value.id).fold(
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