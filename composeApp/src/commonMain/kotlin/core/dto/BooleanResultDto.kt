package core.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BooleanResultDto(
    @SerialName("result")
    val result: Boolean
)
