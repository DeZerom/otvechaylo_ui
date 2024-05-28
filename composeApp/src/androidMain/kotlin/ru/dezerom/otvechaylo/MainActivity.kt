package ru.dezerom.otvechaylo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import app.App
import app.config.CURRENT_CONFIG
import app.config.LocalAppConfig
import app.navigation.AuthNavComponentImpl
import com.arkivanov.decompose.DefaultComponentContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CompositionLocalProvider(
                LocalAppConfig provides CURRENT_CONFIG
            ) {
                App(AuthNavComponentImpl(DefaultComponentContext(lifecycle)))
            }
        }
    }
}
