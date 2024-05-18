package core.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StringResultDto(
    @SerialName("result")
    val result: String
)
