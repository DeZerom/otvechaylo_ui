package features.contexts.presentation.component

import app.config.AppConfig
import app.config.CURRENT_CONFIG
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import core.components.BaseCoroutineComponent
import core.components.SnackBarComponent
import core.components.StateComponent
import core.components.TextInputComponent
import core.utils.text_validators.LengthValidator
import features.contexts.domain.model.ContextLightweight
import features.contexts.domain.model.ContextSource
import features.contexts.domain.use_case.ContextUseCase
import features.contexts.presentation.model.ContextDetailScreenState
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ContextDetailComponent(
    componentContext: ComponentContext,
    private val onBackPressed: () -> Unit
): BaseCoroutineComponent(componentContext), KoinComponent {
    val questionComponent = TextInputComponent(LengthValidator.notEmpty())
    val answerComponent = AnswerComponent(componentContext.childContext("answer"))
    val stateComponent = StateComponent(ContextDetailScreenState())
    val snackBarComponent = SnackBarComponent()

    private val useCase: ContextUseCase by inject()

    fun setContext(context: ContextLightweight) {
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
        } else {
            showContext()
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
        stateComponent.reduce { copy(isLoading = true) }

        useCase.getRich(stateComponent.state.value.id).fold(
            onSuccess = {
                stateComponent.reduce {
                    copy(
                        isLoading = false,
                        isShown = true,
                        context = it.context,
                        isFullyLoaded = true
                    )
                }
            },
            onFailure = {
                stateComponent.reduce { copy(isLoading = false) }
                snackBarComponent.showError(it.message)
            }
        )
    }
}