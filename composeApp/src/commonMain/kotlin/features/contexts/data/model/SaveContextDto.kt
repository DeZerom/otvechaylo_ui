package features.contexts.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SaveContextDto(
    @SerialName("name")
    val name: String,

    @SerialName("description")
    val description: String,

    @SerialName("context")
    val context: String,
)
