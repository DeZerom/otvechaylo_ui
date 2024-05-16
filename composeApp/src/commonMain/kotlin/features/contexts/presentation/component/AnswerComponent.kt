package features.contexts.presentation.component

import com.arkivanov.decompose.ComponentContext
import features.contexts.presentation.model.AnswerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AnswerComponent(
    componentContext: ComponentContext
) {
    private val _state = MutableStateFlow<AnswerState>(AnswerState.Initial)
    val state = _state.asStateFlow()
}