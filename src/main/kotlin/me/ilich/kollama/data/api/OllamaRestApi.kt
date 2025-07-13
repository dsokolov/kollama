package me.ilich.kollama.data.api

import me.ilich.kollama.data.model.ChatRequest
import me.ilich.kollama.data.model.ChatResponse
import me.ilich.kollama.data.model.GenerateRequest
import me.ilich.kollama.data.model.GenerateResponse
import me.ilich.kollama.data.model.ShowRequest
import me.ilich.kollama.data.model.ShowResponse
import me.ilich.kollama.data.model.TagsResponse
import me.ilich.kollama.data.model.VersionResponse

/**
 * https://github.com/ollama/ollama/blob/main/docs/api.md
 */
interface OllamaRestApi {

    /**
     * https://github.com/ollama/ollama/blob/main/docs/api.md#version
     */
    suspend fun version(): VersionResponse

    /**
     * https://github.com/ollama/ollama/blob/main/docs/api.md#list-local-models
     */
    suspend fun tags(): TagsResponse

    /**
     * https://github.com/ollama/ollama/blob/main/docs/api.md#show-model-information
     */
    suspend fun show(request: ShowRequest): ShowResponse

    /**
     * https://github.com/ollama/ollama/blob/main/docs/api.md#generate
     */
    suspend fun generate(request: GenerateRequest): GenerateResponse

    /**
     * https://github.com/ollama/ollama/blob/main/docs/api.md#generate-a-chat-completion
     */
    suspend fun chat(request: ChatRequest): ChatResponse

}