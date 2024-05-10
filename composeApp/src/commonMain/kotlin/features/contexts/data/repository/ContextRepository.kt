package features.contexts.data.repository

import features.contexts.data.sources.ContextNetworkSource
import features.contexts.domain.mapper.toDomain
import features.contexts.domain.model.ContextLightweight
import features.contexts.domain.model.ContextSource

class ContextRepository(
    private val networkSource: ContextNetworkSource
) {

    suspend fun getAllLightweight(): Result<List<ContextLightweight>> {
        return networkSource.getAllLightweight().map { list ->
            list.map {
                it.toDomain(
                    source = ContextSource.NETWORK,
                    hasConflict = false
                )
            }
        }
    }

}
