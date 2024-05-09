package core.utils.network

import io.ktor.client.*
import io.ktor.client.request.*

suspend inline fun <reified B: Any?, reified R> HttpClient.makePost(
    url: String,
    body: B
): Result<R> = safeApiCall {
    post {
        url(url)
        if (body != null) setBody(body)
    }
}