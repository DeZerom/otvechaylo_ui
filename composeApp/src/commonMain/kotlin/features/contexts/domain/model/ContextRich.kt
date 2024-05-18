package features.contexts.domain.model

data class ContextRich(
    val id: String,
    val name: String,
    val description: String,
    val hash: String,
    val context: String
)
