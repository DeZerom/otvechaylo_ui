package features.contexts.domain.model

data class ContextLightweight(
    val id: String,
    val name: String,
    val description: String,
    val source: ContextSource,
    val hasConflict: Boolean
)
