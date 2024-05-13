package app.config

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import core.const.SizeConst

@Composable
fun Modifier.fillPlatformWidth() = composed {
    val config = LocalAppConfig.current

    when (config) {
        AppConfig.DESKTOP -> width(SizeConst.Elements.DESKTOP_FIXED_WIDTH)
        AppConfig.MOBILE -> fillMaxWidth()
    }
}
