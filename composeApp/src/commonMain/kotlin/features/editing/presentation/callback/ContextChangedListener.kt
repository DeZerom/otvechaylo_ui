package features.editing.presentation.callback

import features.contexts.domain.model.ContextRich

interface ContextChangedListener {
    fun onChanged(id: String?, payload: ContextChangePayload)

    fun onAdded(context: ContextRich)
}
