package core.widgets.buttons

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import core.const.ShapeConst
import core.const.TextConst

@Composable
fun DefaultButton(
    onClick: () -> Unit,
    text: String,
    textColor: Color,
    backgroundColor: Color,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) = Button(
    onClick = onClick,
    shape = ShapeConst.DEFAULT_SHAPE,
    colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
    modifier = modifier
) {
    if (isLoading) {
        CircularProgressIndicator(color = textColor)
    } else {
        Text(
            text = text,
            style = TextConst.BT.copy(color = textColor)
        )
    }
}
