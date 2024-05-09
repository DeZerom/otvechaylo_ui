@file:OptIn(ExperimentalResourceApi::class)

package core.utils.text_res

import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

@OptIn(ExperimentalResourceApi::class)
sealed interface TextResource {

    class StringTextResource(val string: String): TextResource

    class ResTextResource(val res: StringResource): TextResource

    companion object {
        infix fun TextResource(string: String) = StringTextResource(string)
        infix fun TextResource(res: StringResource) = ResTextResource(res)
    }
}
