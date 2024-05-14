package features.contexts.presentation.model

import features.contexts.domain.model.ContextLightweight

data class ContextsListScreenState(
    val isLoading: Boolean = false,
    val items: List<ContextLightweight> = emptyList()
)
