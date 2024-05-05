import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import app.App
import core.const.ColorConst
import io.kanro.compose.jetbrains.expui.theme.DarkTheme
import io.kanro.compose.jetbrains.expui.window.JBWindow
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import otvechayloui.composeapp.generated.resources.Res
import otvechayloui.composeapp.generated.resources.app_icon
import otvechayloui.composeapp.generated.resources.app_name
import ru.alexgladkov.odyssey.compose.setup.OdysseyConfiguration

@OptIn(ExperimentalResourceApi::class)
fun main() = application {
    JBWindow(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.app_name),
        icon = painterResource(Res.drawable.app_icon),
        theme = DarkTheme,
        state = rememberWindowState(
            size = DpSize(width = 1000.dp, height = 800.dp)
        )
    ) {
        App(OdysseyConfiguration(backgroundColor = ColorConst.Background.PRIMARY))
    }
}
