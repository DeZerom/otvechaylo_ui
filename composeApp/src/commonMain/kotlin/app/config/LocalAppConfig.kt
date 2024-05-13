package app.config

import androidx.compose.runtime.compositionLocalOf

val LocalAppConfig = compositionLocalOf<AppConfig> { error("Not provided") }
