package features.contexts.domain.model

import androidx.compose.ui.graphics.Color
import core.const.ColorConst
import core.utils.text_res.TextResource.Companion.TextResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import otvechayloui.composeapp.generated.resources.Res
import otvechayloui.composeapp.generated.resources.both_source
import otvechayloui.composeapp.generated.resources.cloud_source
import otvechayloui.composeapp.generated.resources.local_source

@OptIn(ExperimentalResourceApi::class)
enum class ContextSource {
    NETWORK, DB, BOTH;

    val textResource get() = TextResource(
        res = when(this) {
            NETWORK -> Res.string.cloud_source
            DB -> Res.string.local_source
            BOTH -> Res.string.both_source
        }
    )

    fun getColor(hasConflict: Boolean): Color = when (this) {
        NETWORK -> ColorConst.Colors.BLUE
        DB -> ColorConst.Text.SECONDARY
        BOTH -> if (hasConflict) ColorConst.Colors.ACCENT else ColorConst.Colors.GREEN
    }
}