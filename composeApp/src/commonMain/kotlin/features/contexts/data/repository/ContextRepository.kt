package features.contexts.data.repository

import features.contexts.data.model.ContextRichDto
import features.contexts.data.sources.ContextNetworkSource
import features.contexts.domain.mapper.toDomain
import features.contexts.domain.model.ContextLightweight
import features.contexts.domain.model.ContextRich
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

    suspend fun getRich(id: String): Result<ContextRich> {
        return networkSource.getRich(id).map(ContextRichDto::toDomain)
    }

    suspend fun saveChanges(
        id: String,
        name: String,
        description: String,
        context: String
    ): Result<Boolean> {
        return networkSource.saveChanges(id, name, description, context).map { it.result }
    }

    suspend fun saveContext(
        name: String,
        description: String,
        context: String
    ): Result<ContextRich> {
        return networkSource.saveContext(name, description, context).map(ContextRichDto::toDomain)
    }

}
