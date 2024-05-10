package features.auth.domain.use_case

import features.auth.data.repository.AuthRepository

class AuthUseCase(private val authRepository: AuthRepository) {

    suspend fun authorize(
        login: String,
        password: String
    ): Result<Boolean> {
        return authRepository.authorize(login, password)
    }

    fun isAuthorized() = authRepository.checkToken()

    suspend fun register(
        login: String,
        password: String
    ) = authRepository.register(login, password)

}
