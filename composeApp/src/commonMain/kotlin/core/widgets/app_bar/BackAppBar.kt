package core.widgets.app_bar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.const.ColorConst
import core.const.SizeConst
import core.const.TextConst
import core.widgets.spacings.HorizontalSpacer

@Composable
fun BackAppBar(
    title: String,
    onBackPressed: () -> Unit
) {
    Row(
        modifier = Modifier.padding(start = SizeConst.Padding.M, top = SizeConst.Padding.S)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = null,
            tint = ColorConst.Colors.WHITE,
            modifier = Modifier.clickable { onBackPressed() }
        )
        HorizontalSpacer(SizeConst.Padding.M)
        Text(
            text = title,
            style = TextConst.BTitle,
            modifier = Modifier.padding(start = SizeConst.Padding.M)
        )
    }
}
