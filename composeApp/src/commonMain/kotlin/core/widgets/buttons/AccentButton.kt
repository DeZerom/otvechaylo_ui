package core.widgets.buttons

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.const.ColorConst

@Composable
fun AccentButton(
    onClick: () -> Unit,
    text: String,
    isLoading: Boolean = false,
    modifier: Modifier = Modifier
) = DefaultButton(
    onClick = onClick,
    text = text,
    textColor = ColorConst.Text.PRIMARY,
    backgroundColor = ColorConst.Colors.ACCENT,
    isLoading = isLoading,
    modifier = modifier
)
