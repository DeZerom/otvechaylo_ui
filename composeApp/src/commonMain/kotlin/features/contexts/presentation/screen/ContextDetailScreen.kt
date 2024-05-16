package features.contexts.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.const.ColorConst
import core.const.SizeConst
import core.const.TextConst
import core.widgets.app_bar.BackAppBar
import core.widgets.buttons.AccentButton
import core.widgets.icons.RoundedBgIcon
import core.widgets.spacings.VerticalSpacer
import core.widgets.text_field.DefaultTextField
import features.contexts.presentation.component.ContextDetailComponent
import features.contexts.presentation.widget.AnswerWidget
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import otvechayloui.composeapp.generated.resources.*

@Composable
fun ContextDetailScreen(
    component: ContextDetailComponent?
) {
    if (component == null) {
        Text("qwe")
    } else {
        Content(component)
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Content(
    component: ContextDetailComponent
) {
    val isShown = true

    Scaffold(
        topBar = {
            BackAppBar(
                title = stringResource(Res.string.context),
                onBackPressed = {}
            )
        },
        backgroundColor = ColorConst.Background.PRIMARY
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SizeConst.Padding.M)
        ) {
            Text(
                text = "Name",
                style = TextConst.MTitle
            )
            VerticalSpacer(SizeConst.Padding.M)
            Text(
                text = "Description",
                style = TextConst.MT.copy(color = ColorConst.Text.SECONDARY)
            )
            VerticalSpacer(SizeConst.Padding.L)
            Text(
                text = stringResource(Res.string.content),
                style = TextConst.MTitle
            )
            VerticalSpacer(SizeConst.Padding.XS)
            Text(
                text = stringResource(Res.string.show),
                style = TextConst.MT.copy(color = ColorConst.Colors.ACCENT)
            )
            if (isShown) {
                Text(
                    text = "Context",
                    style = TextConst.MT
                )
            }
            VerticalSpacer(SizeConst.Padding.L)
            DefaultTextField(
                value = "",
                onValueChange = {},
                labelText = stringResource(Res.string.question),
                trailingIcon = { RoundedBgIcon(Icons.AutoMirrored.Outlined.Send) },
                modifier = Modifier.fillMaxWidth()
            )
            VerticalSpacer(SizeConst.Padding.XS)
            AnswerWidget(component = component.answerComponent)
            VerticalSpacer(SizeConst.Padding.M)
            AccentButton(
                onClick = {},
                text = stringResource(Res.string.edit)
            )
        }
    }
}
