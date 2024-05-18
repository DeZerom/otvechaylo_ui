package features.contexts.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContextQuestionDto(
    @SerialName("context")
    val context: String,

    @SerialName("query")
    val question: String
)
