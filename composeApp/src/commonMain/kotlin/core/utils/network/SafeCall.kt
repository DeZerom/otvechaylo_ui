package core.utils.network

import core.utils.text_res.TextResource.Companion.TextResource
import io.ktor.client.call.*
import io.ktor.client.statement.*
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
suspend inline fun <reified T> safeApiCall(
    call: () -> HttpResponse
): Result<T> {
    return try {
        val body = call().body<Any>()

        if (body is T) {
            Result.success(body)
        } else {
            Result.failure(BackErrorThrowable(TextResource((body as ErrorDto).error)))
        }
    } catch (e: Exception) {
        Result.failure(BackErrorThrowable.unknown())
    }
}
