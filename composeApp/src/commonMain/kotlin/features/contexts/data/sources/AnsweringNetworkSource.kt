package features.contexts.data.sources

import core.dto.StringResultDto
import core.utils.network.makeGet
import core.utils.network.makePost
import features.contexts.data.model.ContextQuestionDto
import io.ktor.client.*

class AnsweringNetworkSource(
    private val client: HttpClient
) {

    suspend fun getAnswerById(
        id: String,
        question: String
    ): Result<StringResultDto> {
        return client.makeGet(
            url = "/answer",
            parameters = listOf(
                Pair("context_id", id),
                Pair("query", question)
            )
        )
    }

    suspend fun getAnswerByContext(
        context: String,
        question: String
    ): Result<StringResultDto> {
        return client.makePost(
            url = "/answer",
            body = ContextQuestionDto(context, question)
        )
    }

}
