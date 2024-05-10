package app.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.Value
import features.auth.presentation.component.AuthComponent
import features.auth.presentation.component.RegistrationComponent
import kotlinx.serialization.Serializable

class AuthNavComponentImpl(
    componentContext: ComponentContext
): ComponentContext by componentContext, AuthNavComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack: Value<ChildStack<*, AuthNavComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Auth,
        handleBackButton = true,
        childFactory = ::createChild,
        serializer = ChildConfig.serializer()
    )

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext,
    ) = when (config) {
        ChildConfig.Auth -> AuthNavComponent.Child.Auth(
            AuthComponent(
                componentContext = componentContext,
                navigateToRegistration = { navigation.push(ChildConfig.Registration) }
            )
        )

        ChildConfig.Registration -> AuthNavComponent.Child.Registration(
            RegistrationComponent(
                componentContext = componentContext,
                onRegister = { navigation.pop() }
            )
        )
    }

    @Serializable
    private sealed interface ChildConfig {
        @Serializable
        data object Auth: ChildConfig

        @Serializable
        data object Registration: ChildConfig
    }
}
