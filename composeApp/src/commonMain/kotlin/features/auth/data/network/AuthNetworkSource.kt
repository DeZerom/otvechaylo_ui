package features.auth.data.network

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

}
