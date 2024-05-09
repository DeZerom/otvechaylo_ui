package app.network.client

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

val client = HttpClient {
    install(ContentNegotiation) { json() }
    defaultRequest {
        url("http://127.0.0.1:8080")
        contentType(ContentType.Application.Json)
    }
}
