package core.widgets.app_bar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.const.SizeConst
import core.const.TextConst

@Composable
fun TitleAppBar(
    title: String
) {
    Text(
        text = title,
        style = TextConst.BTitle,
        modifier = Modifier.padding(start = SizeConst.Padding.M, top = SizeConst.Padding.S)
    )
}
