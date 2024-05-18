package features.contexts.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
sealed interface AnswerState {

    @Immutable
    data object Initial: AnswerState

    @Immutable
    data object Loading: AnswerState

    @Immutable
    data object NotFound: AnswerState

    @Immutable
    data class Error(val message: String): AnswerState

    @Immutable
    data class Answer(val answer: String): AnswerState

}
