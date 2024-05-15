package features.contexts.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ContentPasteSearch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.config.fillPlatformWidth
import core.const.ColorConst
import core.const.SizeConst
import core.const.TextConst
import core.utils.extension.collectAsState
import core.widgets.app_bar.TitleAppBar
import core.widgets.icons.RoundedBgIcon
import core.widgets.loader.Loader
import core.widgets.snack_bar_handler.SnackBarHandlerScaffold
import features.contexts.presentation.component.ContextsListComponent
import features.contexts.presentation.model.ContextsListScreenState
import features.contexts.presentation.widget.ContextListItem
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import otvechayloui.composeapp.generated.resources.Res
import otvechayloui.composeapp.generated.resources.add_context
import otvechayloui.composeapp.generated.resources.contexts
import otvechayloui.composeapp.generated.resources.there_is_empty

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ContextsListScreen(
    component: ContextsListComponent
) {
    val state by component.state.collectAsState()

    SnackBarHandlerScaffold(
        snackBarComponent = component.snackBarComponent,
        topBar = { TitleAppBar(title = stringResource(Res.string.contexts)) },
        modifier = Modifier.fillPlatformWidth()
    ) {
        if (state.isLoading) {
            Loader(modifier = Modifier.fillMaxSize())
        } else {
            Content(state)
        }
    }
}

@Composable
private fun Content(
    state: ContextsListScreenState
) {
    if (state.items.isNotEmpty()) {
        LazyColumn(
            contentPadding = PaddingValues(SizeConst.Padding.M)
        ) {
            items(
                key = { state.items[it].id },
                count = state.items.size
            ) {
                ContextListItem(
                    context = state.items[it],
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = SizeConst.Padding.M)
                )
            }
        }
    } else {
        Placeholder()
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Placeholder() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(SizeConst.Padding.XS),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            RoundedBgIcon(
                icon = Icons.Outlined.ContentPasteSearch,
                iconSize = SizeConst.IconSize.XXL,
                iconPadding = SizeConst.Padding.XS
            )
            Text(
                text = stringResource(Res.string.there_is_empty),
                style = TextConst.MTitle
            )
            Text(
                text = stringResource(Res.string.add_context),
                style = TextConst.MT.copy(color = ColorConst.Text.SECONDARY)
            )
        }
    }
}
