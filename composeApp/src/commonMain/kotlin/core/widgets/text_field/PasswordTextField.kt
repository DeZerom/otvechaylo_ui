package core.widgets.text_field

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.PasswordVisualTransformation
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import otvechayloui.composeapp.generated.resources.Res
import otvechayloui.composeapp.generated.resources.password

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PasswordTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    labelText: String = stringResource(Res.string.password)
) = DefaultTextField(
    value = value,
    onValueChange = onValueChanged,
    labelText = labelText,
    visualTransformation = PasswordVisualTransformation('*')
)
