package features.auth.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import core.const.ColorConst
import core.const.SizeConst
import core.const.TextConst
import core.widgets.buttons.WhiteButton
import core.widgets.spacings.VerticalSpacer
import core.widgets.text_field.DefaultTextField
import core.widgets.text_field.PasswordTextField
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import otvechayloui.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AuthScreen() {
    var value by remember { mutableStateOf("") }
    var passValue by remember { mutableStateOf("") }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = SizeConst.Padding.M)
                .fillMaxHeight()
                .onSizeChanged {  }
        ) {
            Image(
                painter = painterResource(Res.drawable.app_icon),
                contentDescription = null,
                modifier = Modifier.size(SizeConst.IconSize.XXXL)
            )
            VerticalSpacer(SizeConst.Padding.XL)
            Text(
                text = stringResource(Res.string.authorization),
                style = TextConst.BTitle
            )
            Spacer(modifier = Modifier.height(SizeConst.Padding.XXL))
            DefaultTextField(
                value = value,
                onValueChange = { value = it },
                labelText = stringResource(Res.string.login),
            )
            VerticalSpacer(SizeConst.Padding.M)
            PasswordTextField(
                value = passValue,
                onValueChanged = { passValue = it }
            )
            VerticalSpacer(SizeConst.Padding.S)
            Text(
                text = stringResource(Res.string.create_account),
                style = TextConst.ST.copy(color = ColorConst.Text.SECONDARY),
                modifier = Modifier.align(Alignment.Start)
            )
            VerticalSpacer(SizeConst.Padding.XL)
            WhiteButton(
                onClick = {},
                text = stringResource(Res.string.enter),
                modifier = Modifier.width(TextFieldDefaults.MinWidth)
            )
        }
    }
}
