package features.contexts.data.repository

import features.contexts.data.mapper.toLightweight
import features.contexts.data.model.ContextLightweightDto
import features.contexts.data.model.ContextRichDto
import features.contexts.data.sources.ContextDbSource
import features.contexts.data.sources.ContextNetworkSource
import features.contexts.domain.mapper.toDomain
import features.contexts.domain.model.ContextLightweight
import features.contexts.domain.model.ContextRich
import features.contexts.domain.model.ContextSource

class ContextRepository(
    private val networkSource: ContextNetworkSource,
    private val dbSource: ContextDbSource
) {

    suspend fun getAllLightweight(): Result<List<ContextLightweight>> {
        val network = networkSource.getAllLightweight().fold(
            onSuccess = { it },
            onFailure = { return Result.failure(it) }
        )

        val local = dbSource.getAll()

        return Result.success(merge(network, local))
    }

    suspend fun getRich(id: String): Result<ContextRich> {
        return networkSource.getRich(id).map { it.toDomain(ContextSource.NETWORK) }
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
        return networkSource.saveContext(name, description, context).map { it.toDomain(ContextSource.NETWORK) }
    }

    private fun merge(network: List<ContextLightweightDto>, local: List<ContextRichDto>): List<ContextLightweight> {
        val result = mutableListOf<ContextLightweight>()
        val mutableLocal = local.toMutableList()

        for (context in network) {
            val localContext = mutableLocal.firstOrNull { it.id == context.id }

            if (localContext != null) {
                mutableLocal.remove(localContext)
                result.add(
                    context.toDomain(
                        source = ContextSource.BOTH,
                        hasConflict = localContext.hash != context.hash
                    )
                )
            } else {
                result.add(
                    context.toDomain(
                        source = ContextSource.NETWORK,
                        hasConflict = false
                    )
                )
            }
        }

        mutableLocal.forEach {
            result.add(
                it.toLightweight(
                    source = ContextSource.DB,
                    hasConflict = false
                )
            )
        }

        return result
    }
}
