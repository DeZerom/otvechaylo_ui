package app.navigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import features.auth.presentation.component.AuthComponent
import features.auth.presentation.component.RegistrationComponent

interface AuthNavComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class Auth(val component: AuthComponent): Child
        class Registration(val component: RegistrationComponent): Child
    }
}
