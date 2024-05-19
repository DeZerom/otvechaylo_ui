package features.editing.presentation.callback

import features.contexts.domain.model.ContextSource

data class ContextChangePayload(
    val name: String,
    val description: String,
    val context: String,
    val source: ContextSource
)
