package features.contexts.presentation.component

import com.arkivanov.decompose.ComponentContext
import core.components.BaseCoroutineComponent
import core.components.SnackBarComponent
import core.components.StateComponent
import features.contexts.domain.model.ContextLightweight
import features.contexts.domain.use_case.ContextUseCase
import features.contexts.presentation.model.ContextsListScreenState
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ContextsListComponent(
    componentContext: ComponentContext,
    private val onContextClicked: (ContextLightweight) -> Unit
) : BaseCoroutineComponent(componentContext), KoinComponent {
    val state = StateComponent(ContextsListScreenState())
    val snackBarComponent = SnackBarComponent()

    private val useCase: ContextUseCase by inject()

    init {
        getList()
    }

    fun onClicked(context: ContextLightweight) {
        onContextClicked(context)
    }

    private fun getList() = componentScope.launch {
        state.reduce { copy(isLoading = true) }

        useCase.getAllLightweight().fold(
            onSuccess = { state.reduce { copy(items = it) } },
            onFailure = { snackBarComponent.showError(it.message) }
        )

        state.reduce { copy(isLoading = false) }
    }
}
