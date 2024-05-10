package core.components

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StateComponent<T>(initial: T) {
    private val _state = MutableStateFlow(initial)
    val state = _state.asStateFlow()

    fun setState(state: T) {
        _state.value = state
    }

    fun reduce(block: T.() -> T) {
        _state.value = copyState(block)
    }

    fun copyState(block: T.() -> T) = block(state.value)
}

