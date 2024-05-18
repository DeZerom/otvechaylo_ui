package features.contexts.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.config.fillPlatformWidth
import core.const.ColorConst
import core.const.SizeConst
import core.const.TextConst
import core.utils.extension.collectAsState
import core.utils.extension.collectTextAsState
import core.widgets.app_bar.BackAppBar
import core.widgets.buttons.AccentButton
import core.widgets.dialogs.YesOrNoAlertDialog
import core.widgets.icons.RoundedBgIcon
import core.widgets.loader.Loader
import core.widgets.snack_bar_handler.SnackBarHandlerScaffold
import core.widgets.spacings.VerticalSpacer
import core.widgets.text_field.DefaultTextField
import features.contexts.presentation.component.ContextDetailComponent
import features.contexts.presentation.widget.AnswerWidget
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import otvechayloui.composeapp.generated.resources.*

@Composable
fun ContextDetailScreen(
    component: ContextDetailComponent
) {
    val state by component.stateComponent.collectAsState()

    if (state.id.isEmpty()) {
        Placeholder()
    } else {
        Content(component)
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Content(
    component: ContextDetailComponent
) {
    val state by component.stateComponent.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    SnackBarHandlerScaffold(
        topBar = {
            BackAppBar(
                title = stringResource(Res.string.context),
                onBackPressed = component::onBackPressed
            )
        },
        snackBarComponent = component.snackBarComponent
    ) {
        if (showDialog) {
            YesOrNoAlertDialog(
                title = stringResource(Res.string.context_not_loaded),
                text = stringResource(Res.string.context_not_loaded_message),
                yesText = stringResource(Res.string.yes),
                noText = stringResource(Res.string.no),
                onYesClicked = { component.onShowHideClicked() },
                onDismiss = { showDialog = false }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SizeConst.Padding.M)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = state.name,
                style = TextConst.MTitle
            )
            VerticalSpacer(SizeConst.Padding.M)
            Text(
                text = state.description,
                style = TextConst.MT.copy(color = ColorConst.Text.SECONDARY)
            )
            VerticalSpacer(SizeConst.Padding.L)
            Text(
                text = stringResource(Res.string.content),
                style = TextConst.MTitle
            )
            VerticalSpacer(SizeConst.Padding.XS)
            Text(
                text = if (state.isShown)
                    stringResource(Res.string.hide)
                else
                    stringResource(Res.string.show),
                style = TextConst.MT.copy(color = ColorConst.Colors.ACCENT),
                modifier = Modifier.clickable {
                    if (state.isFullyLoaded) {
                        component.onShowHideClicked()
                    } else {
                        showDialog = true
                    }
                }
            )
            if (state.isShown) {
                VerticalSpacer(height = SizeConst.Padding.XS)
                Text(
                    text = state.context,
                    style = TextConst.MT
                )
            } else if (state.isLoading) {
                VerticalSpacer(height = SizeConst.Padding.XS)
                Loader(color = ColorConst.Colors.WHITE)
            }
            VerticalSpacer(SizeConst.Padding.L)
            DefaultTextField(
                value = component.questionComponent.collectTextAsState().value,
                onValueChange = { component.questionComponent.onChanged(it) },
                labelText = stringResource(Res.string.question),
                trailingIcon = { RoundedBgIcon(Icons.AutoMirrored.Outlined.Send) },
                modifier = Modifier.fillMaxWidth()
            )
            VerticalSpacer(SizeConst.Padding.XS)
            AnswerWidget(
                component = component.answerComponent,
                modifier = Modifier.fillMaxWidth()
            )
            VerticalSpacer(SizeConst.Padding.M)
            AccentButton(
                onClick = {},
                text = stringResource(Res.string.edit),
                modifier = Modifier.fillPlatformWidth()
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Placeholder() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = ColorConst.Background.PRIMARY)
    ) {
        RoundedBgIcon(
            icon = Icons.Filled.QuestionMark,
            iconSize = SizeConst.IconSize.XXL,
            iconPadding = SizeConst.Padding.XS
        )
        VerticalSpacer(height = SizeConst.Padding.M)
        Text(
            text = stringResource(Res.string.empty_detail_title),
            style = TextConst.MTitle
        )
        VerticalSpacer(height = SizeConst.Padding.XS)
        Text(
            text = stringResource(Res.string.empty_detail_message),
            style = TextConst.MT.copy(color = ColorConst.Text.SECONDARY)
        )
    }
}
