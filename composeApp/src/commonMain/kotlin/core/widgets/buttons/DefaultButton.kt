package core.widgets.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import core.const.ShapeConst
import core.const.SizeConst
import core.const.TextConst
import core.widgets.loader.Loader

@Composable
fun DefaultButton(
    onClick: () -> Unit,
    text: String,
    textColor: Color,
    backgroundColor: Color,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) = Button(
    onClick = remember(isLoading) {
        if (isLoading) { {} }
        else onClick
    },
    shape = ShapeConst.DEFAULT_SHAPE,
    colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
    modifier = Modifier
        .height(SizeConst.Elements.DEFAULT_BUTTON_HEIGHT)
        .then(modifier)
) {
    if (isLoading) {
        Loader(
            color = textColor
        )
    } else {
        Text(
            text = text,
            style = TextConst.BT.copy(color = textColor)
        )
    }
}
