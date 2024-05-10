package features.auth.data.persistence

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get

class AuthSettingsSource {
    private val settings = Settings()

    fun saveToken(token: String) {
        settings.putString(TOKEN_KEY, token)
    }

    fun getToken(): String? = settings[TOKEN_KEY]

    companion object {
        const val TOKEN_KEY = "token_key"
    }
}
