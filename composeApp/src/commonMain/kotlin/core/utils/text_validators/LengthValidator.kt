package core.utils.text_validators

class LengthValidator(
    private val minLength: Int,
    private val maxLength: Int = Int.MAX_VALUE
): TextValidator {
    override fun validate(text: String): Boolean = text.length in minLength..maxLength

    companion object {
        fun notEmpty() = LengthValidator(minLength = 1)
    }
}
