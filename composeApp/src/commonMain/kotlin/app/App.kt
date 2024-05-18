package app

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import app.navigation.AuthNavComponent
import com.arkivanov.decompose.extensions.compose.stack.Children
import features.auth.presentation.screen.AuthScreen
import features.auth.presentation.screen.RegistrationScreen
import features.contexts.presentation.screen.ContextDetailScreen
import features.contexts.presentation.screen.ContextScreenManager
import features.contexts.presentation.screen.ContextsListScreen
import features.editing.presentation.screen.ContextEditingScreen
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
                is AuthNavComponent.Child.Registration -> RegistrationScreen(child.component)
                is AuthNavComponent.Child.ContextManager -> ContextScreenManager(child.component)
                is AuthNavComponent.Child.Contexts -> ContextsListScreen(child.component)
                is AuthNavComponent.Child.ContextDetail -> ContextDetailScreen(child.component)
                is AuthNavComponent.Child.Edit -> ContextEditingScreen(child.component)
            }
        }
    }
}

