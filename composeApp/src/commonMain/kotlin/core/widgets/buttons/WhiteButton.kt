package core.widgets.buttons

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.const.ColorConst

@Composable
fun WhiteButton(
    onClick: () -> Unit,
    text: String,
    isLoading: Boolean = false,
    modifier: Modifier = Modifier
) = DefaultButton(
    onClick = onClick,
    text = text,
    textColor = ColorConst.Text.DARK,
    backgroundColor = ColorConst.Background.WHITE,
    isLoading = isLoading,
    modifier = modifier
)
