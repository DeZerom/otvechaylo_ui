package core.components

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

open class BaseCoroutineComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {
    private val scopeKeeperInstance = componentContext.instanceKeeper.getOrCreate {
        CoroutineScopeInstanceKeeper()
    }

    protected val componentScope = scopeKeeperInstance.scope

    class CoroutineScopeInstanceKeeper: InstanceKeeper.Instance {
        val scope = CoroutineScope(SupervisorJob())
    }
}