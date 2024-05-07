package app.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import features.auth.presentation.component.auth.AuthComponent
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
            AuthComponent(componentContext)
        )
    }

    @Serializable
    private sealed interface ChildConfig {
        @Serializable
        data object Auth: ChildConfig
    }
}
