package features.editing.presentation.callback

interface ContextChangedListener {
    fun onChanged(id: String?, payload: ContextChangePayload)
}
