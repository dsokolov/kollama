package me.ilich.kollama.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import me.ilich.kollama.api.OllamaRestApi
import me.ilich.kollama.api.data.GenerateRequest
import me.ilich.kollama.api.data.GenerateResponse
import me.ilich.kollama.api.data.ShowRequest
import me.ilich.kollama.api.data.ShowResponse
import me.ilich.kollama.api.data.TagsResponse
import me.ilich.kollama.api.data.VersionResponse
import java.net.URI
import io.ktor.http.*

class OllamaRestApiKtorImpl(
    private val baseUri: URI
) : OllamaRestApi {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    private fun String.resolve(): String =
        baseUri.resolve(this).toASCIIString()

    override suspend fun tags(): TagsResponse =
        client.get("/api/tags".resolve()).body()

    override suspend fun version(): VersionResponse =
        client.get("/api/version".resolve()).body()

    override suspend fun generate(request: GenerateRequest): GenerateResponse {
        return client.post("/api/generate".resolve()) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    override suspend fun show(request: ShowRequest): ShowResponse =
        client.post("/api/show".resolve()) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
}
