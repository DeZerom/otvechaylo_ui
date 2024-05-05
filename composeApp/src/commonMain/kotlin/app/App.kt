package app

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import core.const.ColorConst
import features.auth.presentation.screen.AuthScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme(
        colors = darkColors()
    ) {
        Scaffold(
            backgroundColor = ColorConst.Background.PRIMARY
        ) {
            AuthScreen()
        }
    }
}