package app

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import app.navigation.AuthNavComponent
import com.arkivanov.decompose.extensions.compose.stack.Children
import features.auth.presentation.screen.AuthScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    authNavComponent: AuthNavComponent
) {
    MaterialTheme(
        colors = darkColors()
    ) {
        Children(
            stack = authNavComponent.childStack,
        ) {
            when (val child = it.instance) {
                is AuthNavComponent.Child.Auth -> AuthScreen(child.component)
            }
        }
    }
}