package features.auth.data.repository

import features.auth.data.sources.AuthNetworkSource
import features.auth.data.sources.AuthSettingsSource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.getString
import otvechayloui.composeapp.generated.resources.Res
import otvechayloui.composeapp.generated.resources.unknown_error

class AuthRepository(
    private val networkSource: AuthNetworkSource,
    private val settingsSource: AuthSettingsSource
) {

    @OptIn(ExperimentalResourceApi::class)
    suspend fun authorize(
        login: String,
        password: String
    ): Result<Boolean> {
        val token = settingsSource.getToken()
        if (token != null) {
            return Result.success(true)
        }

        val networkToken = networkSource.authorize(login, password).fold(
            onSuccess = { it.token ?: return Result.failure(UnknownError(getString(Res.string.unknown_error))) },
            onFailure = { return Result.failure(it) }
        )

        settingsSource.saveToken(networkToken)

        return Result.success(true)
    }

    fun checkToken(): Boolean {
        return !settingsSource.getToken().isNullOrBlank()
    }

    suspend fun register(
        login: String,
        password: String
    ): Result<Boolean> {
        return networkSource.register(login, password).map { it.result }
    }

}
