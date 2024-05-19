package features.contexts.data.sources

import core.dto.BooleanResultDto
import core.dto.StringResultDto
import core.utils.network.makeGet
import core.utils.network.makePatch
import core.utils.network.makePost
import features.contexts.data.model.ContextLightweightDto
import features.contexts.data.model.ContextRichDto
import features.contexts.data.model.SaveContextDto
import io.ktor.client.*

class ContextNetworkSource(private val client: HttpClient) {

    suspend fun getAllLightweight(): Result<List<ContextLightweightDto>> {
        return client.makeGet(url = "/all_contexts_light_weight")
    }

    suspend fun getRich(id: String): Result<ContextRichDto> {
        return client.makeGet(
            url = "/context_rich",
            parameters = listOf(Pair("context_id", id))
        )
    }

    suspend fun saveChanges(
        id: String,
        name: String,
        description: String,
        context: String
    ): Result<BooleanResultDto> {
        return client.makePatch(
            url = "/update_context/$id",
            body = SaveContextDto(
                name = name,
                description = description,
                context = context
            )
        )
    }

    suspend fun saveContext(
        name: String,
        description: String,
        context: String
    ): Result<StringResultDto> {
        return client.makePost(
            url = "/save_context",
            body = SaveContextDto(
                name = name,
                description = description,
                context = context
            )
        )
    }

}
