package features.auth.presentation.component.common

import core.utils.text_validators.LengthValidator
import core.utils.text_validators.TextValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginComponent(
    private val validator: TextValidator = LengthValidator.notEmpty()
) {
    private val _login = MutableStateFlow("")
    val login = _login.asStateFlow()

    fun onChanged(newValue: String) {
        _login.value = newValue
    }

    fun validate() = validator.validate(login.value)
}
