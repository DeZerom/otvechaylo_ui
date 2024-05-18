package features.contexts.domain.use_case

import features.contexts.data.repository.ContextRepository
import features.contexts.domain.model.ContextLightweight
import features.contexts.domain.model.ContextRich

class ContextUseCase(
    private val repository: ContextRepository
) {

    suspend fun getAllLightweight(): Result<List<ContextLightweight>> {
        return repository.getAllLightweight()
    }

    suspend fun getRich(id: String): Result<ContextRich> {
        return repository.getRich(id)
    }

}
