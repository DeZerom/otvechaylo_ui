package features.auth.presentation.component

import com.arkivanov.decompose.ComponentContext
import core.components.SnackBarComponent
import core.utils.text_res.TextResource.Companion.TextResource
import core.utils.text_validators.LengthValidator
import features.auth.domain.use_case.AuthUseCase
import features.auth.presentation.component.common.LoginComponent
import features.auth.presentation.component.common.PasswordComponent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import otvechayloui.composeapp.generated.resources.Res
import otvechayloui.composeapp.generated.resources.fill_fields

@OptIn(ExperimentalResourceApi::class)
class AuthComponent(
    componentContext: ComponentContext,
    val navigateToRegistration: () -> Unit,
): ComponentContext by componentContext, KoinComponent {
    val loginComponent = LoginComponent()
    val passwordComponent = PasswordComponent(LengthValidator.notEmpty())
    val snackBarComponent = SnackBarComponent()

    private val authUseCase: AuthUseCase by inject()

    fun authorize() {
        if (!validate()) {
            snackBarComponent.showError(TextResource(res = Res.string.fill_fields))
            return
        }
    }

    private fun validate() = loginComponent.validate()
            && passwordComponent.validate()
}
