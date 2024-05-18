package features.editing.presentation.component

import com.arkivanov.decompose.ComponentContext
import core.components.BaseCoroutineComponent
import core.components.SnackBarComponent
import core.components.TextInputComponent
import core.utils.text_validators.AlwaysValidValidator
import core.utils.text_validators.LengthValidator

class ContextEditingComponent(
    componentContext: ComponentContext,
    private  val onBackPressed: () -> Unit
): BaseCoroutineComponent(componentContext) {
    val nameComponent = TextInputComponent(LengthValidator.notEmpty())
    val descriptionComponent = TextInputComponent(AlwaysValidValidator())
    val contextComponent = TextInputComponent(LengthValidator.notEmpty())

    val snackBarComponent = SnackBarComponent()

    fun onBackPressed() {}

    fun onSavingTypeChange(index: Int) {}

    fun onSaveClicked() {}
}
