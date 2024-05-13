package features.contexts.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.config.fillPlatformWidth
import core.const.ColorConst
import core.const.SizeConst
import core.widgets.app_bar.TitleAppBar
import features.contexts.domain.model.ContextLightweight
import features.contexts.domain.model.ContextSource
import features.contexts.presentation.widget.ContextListItem
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import otvechayloui.composeapp.generated.resources.Res
import otvechayloui.composeapp.generated.resources.contexts

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ContextsListScreen() {
    Scaffold(
        backgroundColor = ColorConst.Background.PRIMARY,
        topBar = { TitleAppBar(title = stringResource(Res.string.contexts)) },
        modifier = Modifier.fillPlatformWidth()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(SizeConst.Padding.M)
        ) {
            items(
                key = { it },
                count = 14
            ) {
                ContextListItem(
                    context = ContextLightweight(
                        id = "",
                        name = "Qwerty",
                        description = "asdfgh",
                        source = ContextSource.NETWORK,
                        hasConflict = false
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = SizeConst.Padding.M)
                )
            }
        }
    }
}
