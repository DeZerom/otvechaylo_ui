package core.widgets.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.const.ColorConst
import core.const.ShapeConst
import core.const.SizeConst
import core.const.TextConst

@Composable
fun YesOrNoAlertDialog(
    title: String,
    text: String,
    yesText: String,
    noText: String,
    onYesClicked: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        title = { Text(text = title, style = TextConst.BTitle) },
        text = { Text(text = text, style = TextConst.MT.copy(color = ColorConst.Text.SECONDARY)) },
        backgroundColor = ColorConst.Background.SECONDARY,
        shape = ShapeConst.DEFAULT_SHAPE,
        confirmButton = {
            Text(
                text = yesText,
                style = TextConst.BT.copy(color = ColorConst.Colors.ACCENT),
                modifier = Modifier
                    .clickable {
                        onYesClicked()
                        onDismiss()
                    }
                    .padding(bottom = SizeConst.Padding.XXS, end = SizeConst.Padding.XXS)
            )
        },
        dismissButton = {
            Text(
                text = noText,
                style = TextConst.BT.copy(color = ColorConst.Text.SECONDARY),
                modifier = Modifier
                    .clickable { onDismiss() }
                    .padding(bottom = SizeConst.Padding.XXS, end = SizeConst.Padding.XXS)
            )
        },
        onDismissRequest = onDismiss,
    )
}
