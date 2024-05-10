package features.contexts.presentation.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.const.ColorConst
import core.const.ShapeConst
import core.const.SizeConst
import core.const.TextConst
import core.utils.text_res.getString
import core.widgets.spacings.VerticalSpacer
import features.contexts.domain.model.ContextLightweight

@Composable
fun ContextListItem(
    context: ContextLightweight,
    modifier: Modifier = Modifier
) {
    Card(
        shape = ShapeConst.DEFAULT_SHAPE,
        backgroundColor = ColorConst.Background.SECONDARY,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = SizeConst.Padding.M,
                vertical = SizeConst.Padding.S
            )
        ) {
            Text(
                text = context.name,
                style = TextConst.MTitle,
                maxLines = 1
            )
            VerticalSpacer(SizeConst.Padding.XXS)
            Text(
                text = context.description,
                style = TextConst.MT.copy(color = ColorConst.Text.SECONDARY),
                maxLines = 3
            )
            VerticalSpacer(SizeConst.Padding.S)
            Text(
                text = context.source.textResource.getString(),
                style = TextConst.ST.copy(
                    color = context.source.getColor(context.hasConflict)
                )
            )
        }
    }
}
