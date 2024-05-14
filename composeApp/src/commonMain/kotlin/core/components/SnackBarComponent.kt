package core.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import core.const.ColorConst
import core.utils.text_res.TextResource
import core.utils.text_res.TextResource.Companion.TextResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SnackBarComponent {
    private val _snackBar = MutableStateFlow<Type?>(null)
    val snackBar = _snackBar.asStateFlow()

    fun showSuccess(res: TextResource) {
        _snackBar.value = Type.Success(message = res)
    }

    fun showSuccess(string: String?) {
        string ?: return
        showSuccess(TextResource(string))
    }

    fun showError(res: TextResource) {
        _snackBar.value = Type.Error(message = res)
    }

    fun showError(string: String?) {
        string ?: return
        showError(TextResource(string))
    }

    sealed class Type {
        abstract val message: TextResource
        abstract val color: Color
        abstract val icon: ImageVector

        class Success(
            override val message: TextResource,
            override val color: Color = ColorConst.SnackBarBg.SUCCESS,
            override val icon: ImageVector = Icons.Outlined.CheckCircle
        ): Type()

        class Error(
            override val message: TextResource,
            override val color: Color = ColorConst.SnackBarBg.ERROR,
            override val icon: ImageVector = Icons.Outlined.ErrorOutline
        ): Type()
    }
}
