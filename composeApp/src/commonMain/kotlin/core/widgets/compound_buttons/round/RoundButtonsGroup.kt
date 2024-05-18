package core.widgets.compound_buttons.round

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.const.ColorConst
import core.const.SizeConst
import core.const.TextConst
import core.widgets.icons.RoundedBgIcon
import core.widgets.spacings.HorizontalSpacer

@Composable
fun RoundButtonsGroup(
    selectedIndex: Int?,
    onChange: (Int) -> Unit,
    items: List<RoundButtonState>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(SizeConst.Padding.XS)
    ) {
        items.forEachIndexed { index, item ->
            RoundButton(
                state = item,
                isSelected = index == selectedIndex,
                onClicked = { onChange(index) }
            )
        }
    }
}

@Composable
private fun RoundButton(
    state: RoundButtonState,
    onClicked: () -> Unit,
    isSelected: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onClicked() }
    ) {
        if (isSelected) {
            RoundedBgIcon(
                icon = Icons.Outlined.Check,
                iconSize = SizeConst.IconSize.MMinus
            )
        } else {
            Card(
                backgroundColor = Color.Transparent,
                border = BorderStroke(width = 1.dp, color = ColorConst.Colors.ACCENT),
                shape = CircleShape,
                content = {},
                modifier = Modifier.size(SizeConst.IconSize.L)
            )
        }
        HorizontalSpacer(width = SizeConst.Padding.XS)
        Text(
            text = state.title,
            style = TextConst.MT
        )
    }
}
