package features.contexts.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class ContextDetailScreenState(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val context: String = "",
    val isFullyLoaded: Boolean = false,
    val isShown: Boolean = false,
    val isLoading: Boolean = false,
)
