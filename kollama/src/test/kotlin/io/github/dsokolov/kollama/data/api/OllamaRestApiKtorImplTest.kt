package io.github.dsokolov.kollama.data.api

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import io.github.dsokolov.kollama.data.model.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.net.URI

class OllamaRestApiKtorImplTest {
    private fun createApiWithMockEngine(handler: MockRequestHandler): OllamaRestApiKtorImpl {
        val mockEngine = MockEngine { request -> handler(request) }
        val client = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true; isLenient = true })
            }
        }
        return OllamaRestApiKtorImpl(URI.create("http://localhost:1234"), client)
    }

    private fun readJsonFile(filename: String): String {
        return javaClass.getResourceAsStream("/io/github/dsokolov/kollama/data/api/$filename")
            ?.bufferedReader()
            ?.use { it.readText() }
            ?: throw IllegalArgumentException("File not found: $filename")
    }

    @Test
    fun `version returns correct response`() = runBlocking {
        val expected = VersionResponse(version = "1.2.3")
        val api = createApiWithMockEngine { _ ->
            respond(
                content = readJsonFile("version_response.json"),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }
        val result = api.version()
        assertEquals(expected, result)
    }

    @Test
    fun `tags returns correct response`() = runBlocking {
        val expected = TagsResponse(
            models = listOf(
                TagsResponse.Model(
                    name = "llama2",
                    modifiedAt = "2024-01-01T00:00:00Z",
                    size = 123456L,
                    digest = "digest1",
                    details = TagsResponse.Details(
                        format = "gguf",
                        family = "llama",
                        families = listOf("llama"),
                        parameterSize = "7B",
                        quantizationLevel = "Q4"
                    )
                )
            )
        )
        val api = createApiWithMockEngine { _ ->
            respond(
                content = readJsonFile("tags_response.json"),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }
        val result = api.tags()
        assertEquals(expected, result)
    }

    @Test
    fun `generate returns correct response`() = runBlocking {
        val expected = GenerateResponse(response = "Hello!", done = true)
        val api = createApiWithMockEngine { _ ->
            respond(
                content = readJsonFile("generate_response.json"),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }
        val req = GenerateRequest(model = "llama2", prompt = "Hi", stream = false, options = null)
        val result = api.generate(req)
        assertEquals(expected, result)
    }

    @Test
    fun `chat returns correct response`() = runBlocking {
        val expected = ChatResponse(
            model = "llama2",
            message = ChatResponse.Message(role = "assistant", content = "Hi!")
        )
        val api = createApiWithMockEngine { _ ->
            respond(
                content = readJsonFile("chat_response.json"),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }
        val req = ChatRequest(model = "llama2", messages = emptyList(), stream = false)
        val result = api.chat(req)
        assertEquals(expected, result)
    }
} 