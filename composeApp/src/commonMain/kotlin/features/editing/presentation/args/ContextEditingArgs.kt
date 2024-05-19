package features.editing.presentation.args

import features.contexts.domain.model.ContextSource
import kotlinx.serialization.Serializable

@Serializable
data class ContextEditingArgs(
    val id: String,
    val source: ContextSource,
    val hasConflict: Boolean
)
