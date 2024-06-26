package features.auth.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.config.fillPlatformWidth
import core.const.SizeConst
import core.const.TextConst
import core.utils.extension.collectAsState
import core.utils.extension.collectTextAsState
import core.widgets.buttons.WhiteButton
import core.widgets.snack_bar_handler.SnackBarHandlerScaffold
import core.widgets.spacings.VerticalSpacer
import core.widgets.text_field.DefaultTextField
import core.widgets.text_field.PasswordTextField
import features.auth.presentation.component.RegistrationComponent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import otvechayloui.composeapp.generated.resources.Res
import otvechayloui.composeapp.generated.resources.app_icon
import otvechayloui.composeapp.generated.resources.login
import otvechayloui.composeapp.generated.resources.register
import otvechayloui.composeapp.generated.resources.registration
import otvechayloui.composeapp.generated.resources.repeat_password

@OptIn(ExperimentalResourceApi::class)
@Composable
fun RegistrationScreen(
    component: RegistrationComponent
) {
    val state by component.stateComponent.collectAsState()

    SnackBarHandlerScaffold(
        snackBarComponent = component.snackBarComponent,
        modifier = Modifier.fillPlatformWidth()
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
                    text = stringResource(Res.string.registration),
                    style = TextConst.BTitle
                )
                Spacer(modifier = Modifier.height(SizeConst.Padding.XXL))
                DefaultTextField(
                    value = component.loginComponent.collectTextAsState().value,
                    onValueChange = component.loginComponent::onChanged,
                    labelText = stringResource(Res.string.login),
                    modifier = Modifier.fillMaxWidth()
                )
                VerticalSpacer(SizeConst.Padding.M)
                PasswordTextField(
                    value = component.passwordComponent.collectTextAsState().value,
                    onValueChanged = component.passwordComponent::onChanged,
                    modifier = Modifier.fillMaxWidth()
                )
                VerticalSpacer(SizeConst.Padding.M)
                PasswordTextField(
                    value = component.repeatPasswordComponent.collectTextAsState().value,
                    onValueChanged = component.repeatPasswordComponent::onChanged,
                    labelText = stringResource(Res.string.repeat_password),
                    modifier = Modifier.fillMaxWidth()
                )
                VerticalSpacer(SizeConst.Padding.XL)
                WhiteButton(
                    onClick = component::register,
                    text = stringResource(Res.string.register),
                    isLoading = state.isLoading,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
