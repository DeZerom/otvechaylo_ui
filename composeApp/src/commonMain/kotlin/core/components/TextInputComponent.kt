package core.components

import core.utils.text_validators.TextValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TextInputComponent(
    private val validator: TextValidator
) {
    private val _value = MutableStateFlow("")
    val value = _value.asStateFlow()

    fun onChanged(newValue: String) {
        _value.value = newValue
    }

    fun validate() = validator.validate(value.value)
}