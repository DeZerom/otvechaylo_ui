package features.auth.presentation.component.common

import core.utils.text_validators.TextValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PasswordComponent(
    private val validator: TextValidator
) {
    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun onChanged(newValue: String) {
        _password.value = newValue
    }

    fun validate() = validator.validate(password.value)
}
