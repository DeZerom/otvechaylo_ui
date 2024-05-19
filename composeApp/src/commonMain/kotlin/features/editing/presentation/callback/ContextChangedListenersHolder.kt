package features.editing.presentation.callback

import features.contexts.domain.model.ContextRich

object ContextChangedListenersHolder {
    private val listeners = mutableListOf<ContextChangedListener>()

    fun register(listener: ContextChangedListener) = listeners.add(listener)

    fun unregister(listener: ContextChangedListener) = listeners.remove(listener)

    fun onContextChanged(
        id: String?,
        payload: ContextChangePayload
    ) = listeners.forEach { it.onChanged(id, payload) }

    fun onContextAdded(context: ContextRich) = listeners.forEach { it.onAdded(context) }
}
