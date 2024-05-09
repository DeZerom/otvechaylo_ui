package core.utils.network

import core.utils.text_res.TextResource
import core.utils.text_res.TextResource.Companion.TextResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import otvechayloui.composeapp.generated.resources.Res
import otvechayloui.composeapp.generated.resources.unknown_error

@OptIn(ExperimentalResourceApi::class)
class BackErrorThrowable(
    val errorMessage: TextResource
): Throwable() {
    companion object {
        fun unknown() = BackErrorThrowable(TextResource(Res.string.unknown_error))
    }
}