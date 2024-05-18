package features.contexts.data.repository

import features.contexts.data.sources.AnsweringNetworkSource

class AnsweringRepository(
    private val source: AnsweringNetworkSource
) {

    suspend fun getAnswerById(
        id: String,
        question: String
    ): Result<String> {
        return source.getAnswerById(id, question).map { it.result }
    }

    suspend fun getAnswerByContext(
        context: String,
        question: String
    ): Result<String> {
        return source.getAnswerByContext(context, question).map { it.result }
    }

}
