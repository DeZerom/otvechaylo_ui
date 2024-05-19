package features.contexts.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnDestroy
import core.components.BaseCoroutineComponent
import core.components.SnackBarComponent
import core.components.StateComponent
import features.contexts.domain.model.ContextLightweight
import features.contexts.domain.use_case.ContextUseCase
import features.contexts.presentation.model.ContextsListScreenState
import features.editing.presentation.callback.ContextChangePayload
import features.editing.presentation.callback.ContextChangedListener
import features.editing.presentation.callback.ContextChangedListenersHolder
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ContextsListComponent(
    componentContext: ComponentContext,
    private val onContextClicked: (ContextLightweight) -> Unit
) : BaseCoroutineComponent(componentContext), KoinComponent, ContextChangedListener {
    val state = StateComponent(ContextsListScreenState())
    val snackBarComponent = SnackBarComponent()

    private val useCase: ContextUseCase by inject()

    init {
        getList()
        ContextChangedListenersHolder.register(this)
        doOnDestroy {
            ContextChangedListenersHolder.unregister(this)
        }
    }

    override fun onChanged(id: String?, payload: ContextChangePayload) {
        state.reduce {
            copy(
                items = state.state.value.items.map {
                    if (it.id == id) {
                        it.copy(
                            name = payload.name,
                            description = payload.description,
                            source = payload.source
                        )
                    } else {
                        it
                    }
                }
            )
        }
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
