package features.auth.data.sources

import core.dto.BooleanResultDto
import core.utils.network.makePost
import features.auth.data.model.AuthResponseDto
import features.auth.data.model.CredentialsDto
import io.ktor.client.*

class AuthNetworkSource(private val client: HttpClient) {

    suspend fun authorize(
        login: String,
        password: String
    ): Result<AuthResponseDto> {
        return client.makePost(
            url = "/auth",
            body = CredentialsDto(login, password)
        )
    }

    suspend fun register(
        login: String,
        password: String
    ): Result<BooleanResultDto> {
        return client.makePost(
            url = "/register",
            body = CredentialsDto(login, password)
        )
    }
}
