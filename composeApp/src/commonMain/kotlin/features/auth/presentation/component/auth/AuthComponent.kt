package features.auth.presentation.component.auth

import com.arkivanov.decompose.ComponentContext
import features.auth.presentation.component.common.LoginComponent
import features.auth.presentation.component.common.PasswordComponent

class AuthComponent(
    componentContext: ComponentContext
): ComponentContext by componentContext {
    val loginComponent = LoginComponent()
    val passwordComponent = PasswordComponent()
}
