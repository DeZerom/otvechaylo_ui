package features.auth.presentation.component

import com.arkivanov.decompose.ComponentContext
import core.components.TextInputComponent
import core.utils.text_validators.AlwaysValidValidator
import core.utils.text_validators.LengthValidator

class RegistrationComponent(
    componentContext: ComponentContext
): ComponentContext by componentContext {
    val loginComponent = TextInputComponent(AlwaysValidValidator())
    val passwordComponent = TextInputComponent(LengthValidator(3))
    val repeatPasswordComponent = TextInputComponent(LengthValidator(3))
}
