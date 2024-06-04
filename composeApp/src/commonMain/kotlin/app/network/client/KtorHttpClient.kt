package app.network.client

import features.auth.data.sources.AuthSettingsSource
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.core.qualifier.StringQualifier

private const val BASE_URL = "http://127.0.0.1:8080"
//private const val BASE_URL = "http://91.146.12.163:8080"
private const val AUTH_HEADER = "AuthTokenHeader"

val DEFAULT_CLIENT = StringQualifier("default_client")
val AUTH_CLIENT = StringQualifier("auth_client")

fun buildClient() = HttpClient {
    install(ContentNegotiation) { json() }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.HEADERS
    }
    defaultRequest {
        url(BASE_URL)
        contentType(ContentType.Application.Json)
    }
}

fun buildAuthClient(authSource: AuthSettingsSource) = HttpClient {
    install(ContentNegotiation) { json() }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.HEADERS
    }
    install(HttpTimeout) {
        requestTimeoutMillis = 60000
        socketTimeoutMillis = 60000
    }
    defaultRequest {
        url(BASE_URL)
        contentType(ContentType.Application.Json)
        header(
            key = AUTH_HEADER,
            value = authSource.getToken() ?: ""
        )
    }
}
