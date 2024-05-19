package features.contexts.domain.use_case

import features.contexts.data.repository.AnsweringRepository
import features.contexts.data.repository.ContextRepository
import features.contexts.domain.model.ContextSource

class AnsweringUseCase(
    private val repository: AnsweringRepository,
    private val contextRepository: ContextRepository
) {

    suspend fun getAnswerById(
        id: String,
        question: String
    ): Result<String> {
        return repository.getAnswerById(id, question)
    }

    suspend fun getAnswerByContext(
        contextId: String,
        context: String,
        question: String
    ): Result<String> {
        val innerContext = context.ifBlank {
            contextRepository.getRich(id = contextId, useLocalBd = true, source = ContextSource.DB).fold(
                onSuccess = { it.context },
                onFailure = { return Result.failure(it) }
            )
        }

        return repository.getAnswerByContext(innerContext, question)
    }

}
