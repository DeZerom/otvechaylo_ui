package core.widgets.icons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import core.const.ColorConst
import core.const.SizeConst

@Composable
fun RoundedBgIcon(
    icon: ImageVector,
    iconColor: Color = ColorConst.Colors.WHITE,
    iconSize: Dp = SizeConst.IconSize.L,
    iconPadding: Dp = SizeConst.Padding.XXS,
    bgColor: Color = ColorConst.Colors.ACCENT,
    modifier: Modifier = Modifier
) {
    Card(
        backgroundColor = bgColor,
        shape = CircleShape,
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(iconPadding)) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(iconSize)
            )
        }
    }
}
