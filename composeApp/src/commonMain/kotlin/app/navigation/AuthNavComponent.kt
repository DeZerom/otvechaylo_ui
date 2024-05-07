package app.navigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import features.auth.presentation.component.auth.AuthComponent

interface AuthNavComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class Auth(val component: AuthComponent): Child
    }
}
