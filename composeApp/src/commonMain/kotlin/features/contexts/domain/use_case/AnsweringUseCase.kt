package features.contexts.domain.use_case

import features.contexts.data.repository.AnsweringRepository

class AnsweringUseCase(
    private val repository: AnsweringRepository
) {

    suspend fun getAnswerById(
        id: String,
        question: String
    ): Result<String> {
        return repository.getAnswerById(id, question)
    }

    suspend fun getAnswerByContext(
        context: String,
        question: String
    ): Result<String> {
        return repository.getAnswerByContext(context, question)
    }

}
