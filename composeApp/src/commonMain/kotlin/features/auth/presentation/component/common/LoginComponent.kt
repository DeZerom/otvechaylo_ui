package features.auth.presentation.component.common

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginComponent {
    private val _login = MutableStateFlow("")
    val login = _login.asStateFlow()

    fun onChanged(newValue: String) {
        _login.value = newValue
    }

    fun validate() = login.value.length > 3
}
