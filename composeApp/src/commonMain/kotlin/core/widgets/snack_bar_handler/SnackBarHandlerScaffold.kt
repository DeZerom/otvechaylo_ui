package core.widgets.snack_bar_handler

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import core.components.SnackBarComponent
import core.const.ColorConst
import core.utils.text_res.getStringSuspending

@Composable
fun SnackBarHandlerScaffold(
    snackBarComponent: SnackBarComponent,
    backgroundColor: Color = ColorConst.Background.PRIMARY,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit
) {
    val state = rememberScaffoldState()
    val type by snackBarComponent.snackBar.collectAsState()

    LaunchedEffect(type) {
        type?.let {
            state.snackbarHostState.showSnackbar(message = it.message.getStringSuspending())
        }
    }

    Scaffold(
        scaffoldState = state,
        backgroundColor = backgroundColor,
        snackbarHost = { DefaultSnackBarHost(state = it, type = type) },
        content = content
    )
}
