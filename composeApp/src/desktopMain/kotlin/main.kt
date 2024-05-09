import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import app.App
import app.di.appModule
import app.navigation.AuthNavComponentImpl
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import features.auth.data.di.authDiModule
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

    val root = runOnUiThread {
        AuthNavComponentImpl(DefaultComponentContext(lifecycle = lifecycle))
    }

    startKoin {
        modules(appModule(), authDiModule)
    }

    application {
        val windowState = rememberWindowState(size = DpSize(width = 1000.dp, height = 800.dp))

        LifecycleController(lifecycle, windowState)

        JBWindow(
            onCloseRequest = ::exitApplication,
            title = stringResource(Res.string.app_name),
            icon = painterResource(Res.drawable.app_icon),
            theme = DarkTheme,
            state = windowState
        ) {
            App(root)
        }
    }
}
