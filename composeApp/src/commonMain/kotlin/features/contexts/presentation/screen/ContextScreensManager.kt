package features.contexts.presentation.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.config.AppConfig
import app.config.LocalAppConfig
import core.const.ColorConst
import features.contexts.presentation.component.ContextScreenManagerComponent

@Composable
fun ContextScreenManager(
    component: ContextScreenManagerComponent
) {
    if (LocalAppConfig.current == AppConfig.DESKTOP) {
        WideContextsScreen(component)
    }
}

@Composable
fun WideContextsScreen(
    component: ContextScreenManagerComponent
) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        ContextsListScreen(component.listComponent)
        Divider(
            color = ColorConst.Text.SECONDARY,
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        ContextDetailScreen(null)
    }
}
