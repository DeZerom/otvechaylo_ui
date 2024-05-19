import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import app.App
import app.config.AppConfig
import app.config.LocalAppConfig
import app.di.dbDiModule
import app.navigation.AuthNavComponentImpl
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import features.auth.di.authDiModule
import features.contexts.di.contextsDiModule
import io.kanro.compose.jetbrains.expui.theme.DarkTheme
import io.kanro.compose.jetbrains.expui.window.JBWindow
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.core.context.startKoin
import otvechayloui.composeapp.generated.resources.Res
import otvechayloui.composeapp.generated.resources.app_icon
import otvechayloui.composeapp.generated.resources.app_name
import utils.runOnUiThread

@OptIn(ExperimentalResourceApi::class)
fun main() {
    val lifecycle = LifecycleRegistry()

    startKoin {
        modules(dbDiModule, authDiModule, contextsDiModule)
    }

    val root = runOnUiThread {
        AuthNavComponentImpl(DefaultComponentContext(lifecycle = lifecycle))
    }

    application {
        val windowState = rememberWindowState(size = DpSize(width = 1000.dp, height = 800.dp))

        LifecycleController(lifecycle, windowState)

        JBWindow(
            onCloseRequest = ::exitApplication,
            title = stringResource(Res.string.app_name),
            icon = painterResource(Res.drawable.app_icon),
            theme = DarkTheme,
            state = windowState,
            resizable = false
        ) {
            CompositionLocalProvider(
                LocalAppConfig provides AppConfig.DESKTOP
            ) {
                App(root)
            }
        }
    }
}
