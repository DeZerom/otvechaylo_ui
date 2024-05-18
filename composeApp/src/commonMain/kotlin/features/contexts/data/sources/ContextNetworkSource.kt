package features.contexts.data.sources

import core.utils.network.makeGet
import features.contexts.data.model.ContextLightweightDto
import features.contexts.data.model.ContextRichDto
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

}
