package features.auth.presentation.component

import com.arkivanov.decompose.ComponentContext
import features.auth.presentation.component.common.LoginComponent
import features.auth.presentation.component.common.PasswordComponent

class RegistrationComponent(
    componentContext: ComponentContext
): ComponentContext by componentContext {
    val loginComponent = LoginComponent()
    val passwordComponent = PasswordComponent()
    val repeatPasswordComponent = PasswordComponent()
}
