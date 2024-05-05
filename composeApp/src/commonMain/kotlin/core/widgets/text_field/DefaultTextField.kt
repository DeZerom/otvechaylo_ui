package core.widgets.text_field

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import core.const.ColorConst
import core.const.ShapeConst
import core.const.TextConst

@Composable
fun DefaultTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    isSingleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextConst.MT,
        label = {
            if (value.isBlank() && !isFocused) {
                Text(
                    text = labelText,
                    style = TextConst.MT.copy(color = ColorConst.Text.SECONDARY)
                )
            } else {
                Text(
                    text = labelText,
                    style = TextConst.ST.copy(color = ColorConst.Text.SECONDARY)
                )
            }
        },
        placeholder = {
            Text(
                text = "ASd",
                style = TextConst.MT.copy(color = ColorConst.Text.SECONDARY)
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = ColorConst.Background.SECONDARY,
            cursorColor = ColorConst.Colors.ACCENT,
            focusedBorderColor = ColorConst.Colors.ACCENT,
            unfocusedBorderColor = Color.Transparent
        ),
        shape = ShapeConst.DEFAULT_SHAPE,
        singleLine = isSingleLine,
        visualTransformation = visualTransformation,
        modifier = Modifier
            .onFocusChanged { isFocused = it.isFocused }
    )
}
