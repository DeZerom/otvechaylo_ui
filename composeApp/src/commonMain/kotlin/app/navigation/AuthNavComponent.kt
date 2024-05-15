package app.navigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import features.auth.presentation.component.AuthComponent
import features.auth.presentation.component.RegistrationComponent
import features.contexts.presentation.component.ContextDetailComponent
import features.contexts.presentation.component.ContextScreenManagerComponent
import features.contexts.presentation.component.ContextsListComponent

interface AuthNavComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class Auth(val component: AuthComponent): Child

        class Registration(val component: RegistrationComponent): Child

        class ContextManager(val component: ContextScreenManagerComponent): Child

        class Contexts(val component: ContextsListComponent): Child

        class ContextDetail(val component: ContextDetailComponent): Child
    }
}
