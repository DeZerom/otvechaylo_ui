package features.auth.presentation.component

import com.arkivanov.decompose.ComponentContext
import core.utils.text_validators.LengthValidator
import features.auth.presentation.component.common.LoginComponent
import features.auth.presentation.component.common.PasswordComponent

class RegistrationComponent(
    componentContext: ComponentContext
): ComponentContext by componentContext {
    val loginComponent = LoginComponent()
    val passwordComponent = PasswordComponent(LengthValidator(3))
    val repeatPasswordComponent = PasswordComponent(LengthValidator(3))
}
