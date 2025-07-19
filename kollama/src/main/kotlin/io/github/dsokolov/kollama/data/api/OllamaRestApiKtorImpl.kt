package io.github.dsokolov.kollama.data.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import java.net.URI
import kotlinx.serialization.json.Json
import io.github.dsokolov.kollama.data.model.ChatRequest
import io.github.dsokolov.kollama.data.model.ChatResponse
import io.github.dsokolov.kollama.data.model.CopyRequest
import io.github.dsokolov.kollama.data.model.CopyResponse
import io.github.dsokolov.kollama.data.model.CreateRequest
import io.github.dsokolov.kollama.data.model.CreateResponse
import io.github.dsokolov.kollama.data.model.DeleteRequest
import io.github.dsokolov.kollama.data.model.DeleteResponse
import io.github.dsokolov.kollama.data.model.EmbeddingsRequest
import io.github.dsokolov.kollama.data.model.EmbeddingsResponse
import io.github.dsokolov.kollama.data.model.GenerateRequest
import io.github.dsokolov.kollama.data.model.GenerateResponse
import io.github.dsokolov.kollama.data.model.PullRequest
import io.github.dsokolov.kollama.data.model.PullResponse
import io.github.dsokolov.kollama.data.model.PushRequest
import io.github.dsokolov.kollama.data.model.PushResponse
import io.github.dsokolov.kollama.data.model.ShowRequest
import io.github.dsokolov.kollama.data.model.ShowResponse
import io.github.dsokolov.kollama.data.model.TagsResponse
import io.github.dsokolov.kollama.data.model.VersionResponse

/**
 * Ktor-based implementation of Ollama REST API client
 * 
 * @param baseUri The base URI of the Ollama server
 * @param httpClient Необязательный кастомный HttpClient (для тестов)
 */
internal class OllamaRestApiKtorImpl(
    private val baseUri: URI,
    private val httpClient: HttpClient? = null
) : OllamaRestApi {

    private val client: HttpClient by lazy {
        httpClient ?: HttpClient {
            install(ContentNegotiation) {
                json(Json { 
                    ignoreUnknownKeys = true 
                    isLenient = true
                })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 120_000 // 2 minutes
                connectTimeoutMillis = 60_000
                socketTimeoutMillis = 120_000
            }
            install(Logging) {
                level = io.ktor.client.plugins.logging.LogLevel.INFO
            }
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
        client.post("/api/generate".resolve()) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()

    override suspend fun chat(request: ChatRequest): ChatResponse =
        client.post("/api/chat".resolve()) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()

    override suspend fun pull(request: PullRequest): PullResponse =
        client.post("/api/pull".resolve()) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()

    override suspend fun push(request: PushRequest): PushResponse =
        client.post("/api/push".resolve()) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()

    override suspend fun create(request: CreateRequest): CreateResponse =
        client.post("/api/create".resolve()) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()

    override suspend fun copy(request: CopyRequest): CopyResponse =
        client.post("/api/copy".resolve()) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()

    override suspend fun delete(request: DeleteRequest): DeleteResponse =
        client.delete("/api/delete".resolve()) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()

    override suspend fun embeddings(request: EmbeddingsRequest): EmbeddingsResponse =
        client.post("/api/embeddings".resolve()) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()

    private fun String.resolve(): String =
        baseUri.resolve(this).toASCIIString()
}
