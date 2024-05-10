package features.contexts.data.sources

import core.utils.network.makeGet
import features.contexts.data.model.ContextLightweightDto
import io.ktor.client.*

class ContextNetworkSource(private val client: HttpClient) {

    suspend fun getAllLightweight(): Result<List<ContextLightweightDto>> {
        return client.makeGet(url = "/all_contexts_light_weight")
    }

}
