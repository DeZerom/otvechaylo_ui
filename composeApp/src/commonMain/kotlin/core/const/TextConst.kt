package core.const

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object TextConst {
    val BTitle = TextStyle(
        color = ColorConst.Text.PRIMARY,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    )

    val MTitle = TextStyle(
        color = ColorConst.Text.PRIMARY,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold
    )

    val BT = TextStyle(
        color = ColorConst.Text.PRIMARY,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    )

    val MT = TextStyle(
        color = ColorConst.Text.PRIMARY,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    )

    val ST = TextStyle(
        color = ColorConst.Text.PRIMARY,
        fontSize = 10.sp,
        fontWeight = FontWeight.Light
    )
}