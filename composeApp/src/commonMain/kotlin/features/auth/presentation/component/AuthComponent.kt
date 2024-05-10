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
import features.auth.presentation.model.AuthScreenState
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import otvechayloui.composeapp.generated.resources.Res
import otvechayloui.composeapp.generated.resources.fill_fields

@OptIn(ExperimentalResourceApi::class)
class AuthComponent(
    componentContext: ComponentContext,
    val navigateToRegistration: () -> Unit,
): BaseCoroutineComponent(componentContext), KoinComponent {

    val loginComponent = TextInputComponent(AlwaysValidValidator())
    val passwordComponent = TextInputComponent(LengthValidator.notEmpty())
    val snackBarComponent = SnackBarComponent()
    val stateComponent = StateComponent(AuthScreenState())

    private val authUseCase: AuthUseCase by inject()

    init {
        checkAuthorization()
    }

    fun authorize() = componentScope.launch {
        if (!validate()) {
            snackBarComponent.showError(TextResource(res = Res.string.fill_fields))
            return@launch
        }

        stateComponent.reduce { copy(isLoading = true) }

        authUseCase.authorize(
            login = loginComponent.value.value,
            password = passwordComponent.value.value
        ).fold(
            onSuccess = { snackBarComponent.showSuccess(TextResource("qwe")) },
            onFailure = { snackBarComponent.showError(it.message) }
        )

        stateComponent.reduce { copy(isLoading = false) }
    }

    private fun validate() = loginComponent.validate()
            && passwordComponent.validate()

    private fun checkAuthorization() {
        if (authUseCase.isAuthorized()) {
            snackBarComponent.showSuccess(TextResource("qwe"))
        }
    }
}
