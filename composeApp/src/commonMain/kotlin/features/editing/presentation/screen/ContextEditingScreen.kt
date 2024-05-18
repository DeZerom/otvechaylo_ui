package features.editing.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.const.ColorConst
import core.const.SizeConst
import core.const.TextConst
import core.utils.extension.collectAsState
import core.utils.extension.collectTextAsState
import core.widgets.app_bar.BackAppBar
import core.widgets.buttons.AccentButton
import core.widgets.compound_buttons.round.RoundButtonState
import core.widgets.compound_buttons.round.RoundButtonsGroup
import core.widgets.loader.Loader
import core.widgets.snack_bar_handler.SnackBarHandlerScaffold
import core.widgets.spacings.VerticalSpacer
import core.widgets.text_field.DefaultTextField
import features.editing.presentation.component.ContextEditingComponent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import otvechayloui.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ContextEditingScreen(
    component: ContextEditingComponent
) {
    SnackBarHandlerScaffold(
        topBar = {
            BackAppBar(
                title = stringResource(Res.string.editing),
                onBackPressed = component::onBackPressed
            )
        },
        snackBarComponent = component.snackBarComponent,
    ) {
        if (component.stateComponent.collectAsState().value.isLoading) {
            Loader(
                color = ColorConst.Colors.WHITE,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Content(component)
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Content(
    component: ContextEditingComponent
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(SizeConst.Padding.M)
    ) {
        DefaultTextField(
            value = component.nameComponent.collectTextAsState().value,
            onValueChange = component.nameComponent::onChanged,
            labelText = stringResource(Res.string.context_name),
            modifier = Modifier.fillMaxWidth()
        )
        VerticalSpacer(height = SizeConst.Padding.XS)
        DefaultTextField(
            value = component.descriptionComponent.collectTextAsState().value,
            onValueChange = component.descriptionComponent::onChanged,
            labelText = stringResource(Res.string.description),
            isSingleLine = false,
            modifier = Modifier.fillMaxWidth()
        )
        VerticalSpacer(height = SizeConst.Padding.XS)
        DefaultTextField(
            value = component.contextComponent.collectTextAsState().value,
            onValueChange = component.contextComponent::onChanged,
            labelText = stringResource(Res.string.content),
            isSingleLine = false,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = SizeConst.Elements.MIN_ANSWER_HEIGHT)
        )
        VerticalSpacer(height = SizeConst.Padding.XL)
        Text(
            text = stringResource(Res.string.saving),
            style = TextConst.BT
        )
        VerticalSpacer(height = SizeConst.Padding.XS)
        RoundButtonsGroup(
            selectedIndex = 0,
            onChange = component::onSavingTypeChange,
            items = listOf(
                RoundButtonState(stringResource(Res.string.locally)),
                RoundButtonState(stringResource(Res.string.on_server))
            )
        )
        VerticalSpacer(height = SizeConst.Padding.M)
        AccentButton(
            onClick = component::onSaveClicked,
            text = stringResource(Res.string.save),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
