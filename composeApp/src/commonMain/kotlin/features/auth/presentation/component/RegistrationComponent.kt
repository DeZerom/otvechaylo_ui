package features.auth.presentation.component

import com.arkivanov.decompose.ComponentContext
import core.components.BaseCoroutineComponent
import core.components.SnackBarComponent
import core.components.StateComponent
import core.components.TextInputComponent
import core.utils.text_res.TextResource.Companion.TextResource
import core.utils.text_validators.AlwaysValidValidator
import core.utils.text_validators.LengthValidator
import features.auth.domain.use_case.AuthUseCase
import features.auth.presentation.model.RegistrationScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.getString
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import otvechayloui.composeapp.generated.resources.Res
import otvechayloui.composeapp.generated.resources.fill_fields
import otvechayloui.composeapp.generated.resources.passwords_must_match
import otvechayloui.composeapp.generated.resources.successful_registration

class RegistrationComponent(
    componentContext: ComponentContext,
    private val onRegister: () -> Unit
): BaseCoroutineComponent(componentContext), KoinComponent {
    val loginComponent = TextInputComponent(AlwaysValidValidator())
    val passwordComponent = TextInputComponent(LengthValidator(3))
    val repeatPasswordComponent = TextInputComponent(LengthValidator(3))
    val snackBarComponent = SnackBarComponent()
    val stateComponent = StateComponent(RegistrationScreenState())

    private val authUseCase: AuthUseCase by inject()

    @OptIn(ExperimentalResourceApi::class)
    fun register() = componentScope.launch {
        if (!validate()) {
            snackBarComponent.showError(getString(Res.string.fill_fields))
            return@launch
        }

        if (passwordComponent.value.value != repeatPasswordComponent.value.value) {
            snackBarComponent.showError(getString(Res.string.passwords_must_match))
            return@launch
        }

        stateComponent.reduce { copy(isLoading = true) }

        authUseCase.register(
            login = loginComponent.value.value,
            password = passwordComponent.value.value
        ).fold(
            onSuccess = {
                snackBarComponent.showSuccess(TextResource(Res.string.successful_registration))
                delay(1500L)
                withContext(Dispatchers.Main) { onRegister() }
            },
            onFailure = { snackBarComponent.showError(it.message) }
        )

        stateComponent.reduce { copy(isLoading = false) }
    }

    private fun validate() = loginComponent.validate()
            && passwordComponent.validate()
            && repeatPasswordComponent.validate()
}
