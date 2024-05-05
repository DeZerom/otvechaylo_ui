package app.navigation

import features.auth.presentation.screen.AuthScreen
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

fun RootComposeBuilder.buildGraph() {
    screen(NavRoutes.AUTH.route) { AuthScreen() }
}
