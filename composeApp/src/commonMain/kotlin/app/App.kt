package app

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import app.navigation.buildGraph
import org.jetbrains.compose.ui.tooling.preview.Preview
import ru.alexgladkov.odyssey.compose.setup.OdysseyConfiguration
import ru.alexgladkov.odyssey.compose.setup.setNavigationContent

@Composable
@Preview
fun App(
    odysseyConfiguration: OdysseyConfiguration
) {
    MaterialTheme(
        colors = darkColors()
    ) {
        setNavigationContent(odysseyConfiguration) {
            buildGraph()
        }
    }
}