package features.auth.data.repository

import features.auth.data.network.AuthNetworkSource

class AuthRepository(private val source: AuthNetworkSource) {

    suspend fun authorize(
        login: String,
        password: String
    ): Result<String> {
        return source.authorize(login, password).map { it.token ?: "" }
    }

}
