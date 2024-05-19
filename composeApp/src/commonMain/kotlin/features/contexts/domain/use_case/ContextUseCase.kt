package features.contexts.domain.use_case

import features.contexts.data.repository.ContextRepository
import features.contexts.domain.model.ContextLightweight
import features.contexts.domain.model.ContextRich
import features.contexts.domain.model.ContextSource

class ContextUseCase(
    private val repository: ContextRepository
) {

    suspend fun getAllLightweight(): Result<List<ContextLightweight>> {
        return repository.getAllLightweight()
    }

    suspend fun getRich(id: String, source: ContextSource, hasConflict: Boolean): Result<ContextRich> {
        return repository.getRich(
            id = id,
            source = source,
            useLocalBd = !hasConflict && source != ContextSource.NETWORK
        )
    }

    suspend fun saveContext(
        onlyLocally: Boolean,
        name: String,
        description: String,
        context: String
    ): Result<ContextRich> {
        return repository.saveContext(name, description, context, onlyLocally)
    }

    suspend fun saveChanges(
        id: String,
        onlyLocally: Boolean,
        name: String,
        description: String,
        context: String
    ): Result<Boolean> {
        return repository.saveChanges(id, name, description, context, onlyLocally)
    }

}
