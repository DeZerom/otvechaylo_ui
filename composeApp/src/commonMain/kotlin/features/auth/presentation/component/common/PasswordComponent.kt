package features.auth.presentation.component.common

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PasswordComponent {
    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun onChanged(newValue: String) {
        _password.value = newValue
    }

    fun validate() = password.value.length > 3
}
