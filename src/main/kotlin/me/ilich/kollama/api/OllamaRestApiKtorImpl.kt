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
import me.ilich.kollama.api.data.TagsResponse
import me.ilich.kollama.api.data.VersionResponse

class OllamaRestApiKtorImpl(
    private val baseUrl: String
) : OllamaRestApi {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    override suspend fun tags(): TagsResponse =
        client.get("$baseUrl/api/tags").body()

    override suspend fun version(): VersionResponse =
        client.get("$baseUrl/api/version").body()

    override suspend fun generate(request: GenerateRequest): GenerateResponse {
        return client.post("$baseUrl/api/generate") {
            setBody(request)
        }.body()
    }
} 