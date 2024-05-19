package features.editing.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class EditingScreenState(
    val isLoading: Boolean = false,
    val selectedIndex: Int = 0,
    val isSaving: Boolean = false,
    val isSavingTypeChooseVisible: Boolean = true
)