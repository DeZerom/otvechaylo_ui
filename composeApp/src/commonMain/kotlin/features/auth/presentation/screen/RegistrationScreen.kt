package features.auth.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import core.const.ColorConst
import core.const.SizeConst
import core.const.TextConst
import core.widgets.buttons.WhiteButton
import core.widgets.spacings.VerticalSpacer
import core.widgets.text_field.DefaultTextField
import core.widgets.text_field.PasswordTextField
import features.auth.presentation.component.RegistrationComponent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import otvechayloui.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun RegistrationScreen(
    component: RegistrationComponent
) {
    Scaffold(
        backgroundColor = ColorConst.Background.PRIMARY
    ) {
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
                    value = component.loginComponent.value.collectAsState().value,
                    onValueChange = component.loginComponent::onChanged,
                    labelText = stringResource(Res.string.login),
                )
                VerticalSpacer(SizeConst.Padding.M)
                PasswordTextField(
                    value = component.passwordComponent.value.collectAsState().value,
                    onValueChanged = component.passwordComponent::onChanged
                )
                VerticalSpacer(SizeConst.Padding.M)
                PasswordTextField(
                    value = component.repeatPasswordComponent.value.collectAsState().value,
                    onValueChanged = component.repeatPasswordComponent::onChanged
                )
                VerticalSpacer(SizeConst.Padding.XL)
                WhiteButton(
                    onClick = {},
                    text = stringResource(Res.string.enter),
                    modifier = Modifier.width(TextFieldDefaults.MinWidth),
                )
            }
        }
    }
}
