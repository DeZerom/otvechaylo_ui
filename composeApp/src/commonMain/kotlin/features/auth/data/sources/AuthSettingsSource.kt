package features.auth.data.sources

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get

class AuthSettingsSource {
    private val settings = Settings()

    fun saveToken(token: String) {
        settings.putString(TOKEN_KEY, token)
    }

    fun getToken(): String? = settings[TOKEN_KEY]

    fun clearToken() = settings.clear()

    companion object {
        const val TOKEN_KEY = "token_key"
    }
}
