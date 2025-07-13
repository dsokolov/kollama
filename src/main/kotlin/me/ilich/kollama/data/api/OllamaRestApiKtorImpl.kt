package me.ilich.kollama.data.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import java.net.URI
import kotlinx.serialization.json.Json
import me.ilich.kollama.data.model.ChatRequest
import me.ilich.kollama.data.model.ChatResponse
import me.ilich.kollama.data.model.CopyRequest
import me.ilich.kollama.data.model.CopyResponse
import me.ilich.kollama.data.model.CreateRequest
import me.ilich.kollama.data.model.CreateResponse
import me.ilich.kollama.data.model.DeleteRequest
import me.ilich.kollama.data.model.DeleteResponse
import me.ilich.kollama.data.model.EmbeddingsRequest
import me.ilich.kollama.data.model.EmbeddingsResponse
import me.ilich.kollama.data.model.GenerateRequest
import me.ilich.kollama.data.model.GenerateResponse
import me.ilich.kollama.data.model.PullRequest
import me.ilich.kollama.data.model.PullResponse
import me.ilich.kollama.data.model.PushRequest
import me.ilich.kollama.data.model.PushResponse
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
