package me.ilich.kollama.data.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import java.net.URI
import kotlinx.serialization.json.Json
import me.ilich.kollama.data.model.ChatRequest
import me.ilich.kollama.data.model.ChatResponse
import me.ilich.kollama.data.model.GenerateRequest
import me.ilich.kollama.data.model.GenerateResponse
import me.ilich.kollama.data.model.ShowRequest
import me.ilich.kollama.data.model.ShowResponse
import me.ilich.kollama.data.model.TagsResponse
import me.ilich.kollama.data.model.VersionResponse

internal class OllamaRestApiKtorImpl(
    private val baseUri: URI
) : OllamaRestApi {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 120_000 // 2 минуты
            connectTimeoutMillis = 60_000
            socketTimeoutMillis = 120_000
        }
    }

    override suspend fun version(): VersionResponse =
        client.get("/api/version".resolve()).body()

    override suspend fun tags(): TagsResponse =
        client.get("/api/tags".resolve()).body()

    override suspend fun show(request: ShowRequest): ShowResponse =
        client.post("/api/show".resolve()) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()

    override suspend fun generate(request: GenerateRequest): GenerateResponse =
        client.post(
            "/api/generate".resolve()
        ) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()

    override suspend fun chat(request: ChatRequest): ChatResponse =
        client.post(
            "/api/chat".resolve()
        ) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()

    private fun String.resolve(): String =
        baseUri.resolve(this).toASCIIString()
}
