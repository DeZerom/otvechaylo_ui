package features.auth.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CredentialsDto(
    @SerialName("login")
    val login: String,

    @SerialName("password")
    val password: String,
)
