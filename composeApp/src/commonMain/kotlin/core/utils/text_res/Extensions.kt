package core.utils.text_res

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.getString as suspendingGetString

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TextResource.getString(): String = when (this) {
    is TextResource.ResTextResource -> stringResource(res)
    is TextResource.StringTextResource -> string
}

@OptIn(ExperimentalResourceApi::class)
suspend fun TextResource.getStringSuspending(): String = when (this) {
    is TextResource.ResTextResource -> suspendingGetString(res)
    is TextResource.StringTextResource -> string
}
