package core.widgets.loader

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import core.const.ColorConst
import core.const.SizeConst

@Composable
fun Loader(
    size: Dp = SizeConst.Elements.PROGRESS_SIZE,
    thickness: Dp = SizeConst.Elements.PROGRESS_THICKNESS,
    color: Color = ColorConst.Background.SECONDARY,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            color = color,
            strokeWidth = thickness,
            modifier = Modifier.size(size)
        )
    }
}
