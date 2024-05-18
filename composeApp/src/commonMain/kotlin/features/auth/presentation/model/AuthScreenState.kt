package features.auth.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class AuthScreenState(
    val isLoading: Boolean = false
)
