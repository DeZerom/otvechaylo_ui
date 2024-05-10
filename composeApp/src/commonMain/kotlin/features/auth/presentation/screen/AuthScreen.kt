package features.auth.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import core.const.ColorConst
import core.const.SizeConst
import core.const.TextConst
import core.utils.extension.collectAsState
import core.widgets.buttons.WhiteButton
import core.widgets.snack_bar_handler.SnackBarHandlerScaffold
import core.widgets.spacings.VerticalSpacer
import core.widgets.text_field.DefaultTextField
import core.widgets.text_field.PasswordTextField
import features.auth.presentation.component.AuthComponent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import otvechayloui.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AuthScreen(
    component: AuthComponent
) {
    val state by component.stateComponent.collectAsState()

    SnackBarHandlerScaffold(
        snackBarComponent = component.snackBarComponent
    ) { paddingValues ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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
                VerticalSpacer(SizeConst.Padding.S)
                Text(
                    text = stringResource(Res.string.create_account),
                    style = TextConst.ST.copy(color = ColorConst.Text.SECONDARY),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .clickable(onClick = component.navigateToRegistration)
                )
                VerticalSpacer(SizeConst.Padding.XL)
                WhiteButton(
                    onClick = component::authorize,
                    text = stringResource(Res.string.enter),
                    isLoading = component.stateComponent.collectAsState().value.isLoading,
                    modifier = Modifier.width(TextFieldDefaults.MinWidth),
                )
            }
        }
    }
}
