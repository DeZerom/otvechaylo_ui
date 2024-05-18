package features.contexts.domain.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Serializable
@Immutable
data class ContextLightweight(
    val id: String,
    val name: String,
    val description: String,
    val source: ContextSource,
    val hasConflict: Boolean
)
