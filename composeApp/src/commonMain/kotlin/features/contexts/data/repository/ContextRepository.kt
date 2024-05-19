package features.contexts.data.repository

import core.utils.extension.toSha256
import features.contexts.data.mapper.toLightweight
import features.contexts.data.model.ContextLightweightDto
import features.contexts.data.model.ContextRichDto
import features.contexts.data.sources.ContextDbSource
import features.contexts.data.sources.ContextNetworkSource
import features.contexts.domain.mapper.toDomain
import features.contexts.domain.model.ContextLightweight
import features.contexts.domain.model.ContextRich
import features.contexts.domain.model.ContextSource
import java.util.*

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
        context: String,
        onlyLocally: Boolean
    ): Result<Boolean> {
        dbSource.saveContext(
            ContextRichDto(
                id = id,
                name = name,
                description = description,
                context = context,
                hash = context.toSha256()
            )
        )

        if (onlyLocally) return Result.success(true)

        return networkSource.saveChanges(id, name, description, context).map { it.result }
    }

    suspend fun saveContext(
        name: String,
        description: String,
        context: String,
        onlyLocally: Boolean
    ): Result<ContextRich> {
        return if (onlyLocally) {
            val dto = ContextRichDto(
                id = UUID.randomUUID().toString(),
                name = name,
                description = description,
                context = context,
                hash = context.toSha256()
            )
            dbSource.saveContext(dto)

            Result.success(dto.toDomain(ContextSource.DB))
        } else {
            val idDto = networkSource.saveContext(name, description, context).fold(
                    onSuccess = { it },
                    onFailure = { return Result.failure(it) }
            )
            val dto = ContextRichDto(
                id = idDto.result,
                name = name,
                description = description,
                context = context,
                hash = context.toSha256()
            )

            dbSource.saveContext(dto)

            Result.success(dto.toDomain(ContextSource.BOTH))
        }
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
