package core.widgets.snack_bar_handler

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import core.components.SnackBarComponent
import core.const.ColorConst
import core.const.ShapeConst
import core.const.SizeConst
import core.const.TextConst
import core.utils.text_res.getString
import core.widgets.spacings.HorizontalSpacer

@Composable
fun DefaultSnackBarHost(
    state: SnackbarHostState,
    type: SnackBarComponent.Type?
) {
    SnackbarHost(state) {
        Snackbar(
            shape = ShapeConst.DEFAULT_SHAPE,
            backgroundColor = type?.color ?: ColorConst.Background.WHITE,
            modifier = Modifier.padding(SizeConst.Padding.L)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = null,
                    modifier = Modifier.size(SizeConst.IconSize.L),
                    tint = Color.White
                )
                HorizontalSpacer(SizeConst.Padding.S)
                Text(
                    text = type?.message?.getString() ?: "",
                    style = TextConst.MT
                )
            }
        }
    }
}
