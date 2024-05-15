package app.navigation

import app.config.AppConfig
import app.config.CURRENT_CONFIG
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.Value
import features.auth.presentation.component.AuthComponent
import features.auth.presentation.component.RegistrationComponent
import features.contexts.presentation.component.ContextDetailComponent
import features.contexts.presentation.component.ContextScreenManagerComponent
import features.contexts.presentation.component.ContextsListComponent
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
                navigateToRegistration = { navigation.push(ChildConfig.Registration) },
                onAuthorized = {
                    navigation.replaceCurrent(
                        when (CURRENT_CONFIG) {
                            AppConfig.DESKTOP -> ChildConfig.ContextManager
                            AppConfig.MOBILE -> ChildConfig.Contexts
                        }
                    )
                }
            )
        )

        ChildConfig.Registration -> AuthNavComponent.Child.Registration(
            RegistrationComponent(
                componentContext = componentContext,
                onRegister = { navigation.pop() }
            )
        )

        ChildConfig.ContextManager -> AuthNavComponent.Child.ContextManager(
            component = ContextScreenManagerComponent(
                componentContext = componentContext,
                listComponent = ContextsListComponent(
                    componentContext = componentContext,
                )
            )
        )

        ChildConfig.Contexts -> AuthNavComponent.Child.Contexts(
            ContextsListComponent(
                componentContext = componentContext
            )
        )

        ChildConfig.ContextDetail -> AuthNavComponent.Child.ContextDetail(
            ContextDetailComponent(
                componentContext = componentContext
            )
        )
    }

    @Serializable
    private sealed interface ChildConfig {
        @Serializable
        data object Auth: ChildConfig

        @Serializable
        data object Registration: ChildConfig

        @Serializable
        data object ContextManager: ChildConfig

        @Serializable
        data object Contexts: ChildConfig

        @Serializable
        data object ContextDetail: ChildConfig
    }
}
