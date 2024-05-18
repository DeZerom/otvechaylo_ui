package features.contexts.presentation.model

import androidx.compose.runtime.Immutable
import features.contexts.domain.model.ContextLightweight

@Immutable
data class ContextsListScreenState(
    val isLoading: Boolean = false,
    val items: List<ContextLightweight> = emptyList()
)
