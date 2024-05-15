package features.contexts.presentation.component

import com.arkivanov.decompose.ComponentContext
import core.components.BaseCoroutineComponent

class ContextScreenManagerComponent(
    componentContext: ComponentContext,
    val listComponent: ContextsListComponent,
): BaseCoroutineComponent(componentContext) {

}
