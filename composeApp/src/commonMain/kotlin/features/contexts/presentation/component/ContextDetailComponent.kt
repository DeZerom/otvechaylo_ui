package features.contexts.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import core.components.BaseCoroutineComponent

class ContextDetailComponent(
    componentContext: ComponentContext
): BaseCoroutineComponent(componentContext) {
    val answerComponent = AnswerComponent(componentContext.childContext("answer"))
}