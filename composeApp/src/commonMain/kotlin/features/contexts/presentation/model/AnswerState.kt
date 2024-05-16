package features.contexts.presentation.model

sealed interface AnswerState {

    data object Initial: AnswerState

    data object Loading: AnswerState

    data object NotFound: AnswerState

    data class Error(val message: String): AnswerState

    data class Answer(val answer: String): AnswerState

}
