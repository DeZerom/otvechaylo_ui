package core.utils.text_validators


class AlwaysValidValidator: TextValidator {
    override fun validate(text: String): Boolean  = true
}
