package core.utils.network

import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.serialization.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.getString
import otvechayloui.composeapp.generated.resources.Res
import otvechayloui.composeapp.generated.resources.unknown_error

@OptIn(ExperimentalResourceApi::class)
@Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
suspend inline fun <reified T> safeApiCall(
    crossinline call: suspend () -> HttpResponse
): Result<T> {
    return try {
        val resp = withContext(Dispatchers.IO) { call() }

        try {
            Result.success(resp.body())
        } catch (e: JsonConvertException) {
            val error = resp.body<ErrorDto>()
            Result.failure(BackErrorThrowable(message = error.error))
        }
    } catch (e: Exception) {
        print(e)
        Result.failure(RuntimeException(getString(Res.string.unknown_error), e))
    }
}
