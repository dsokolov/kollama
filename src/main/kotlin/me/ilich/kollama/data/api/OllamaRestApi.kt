package me.ilich.kollama.data.api

import me.ilich.kollama.data.api.data.*

/**
 * https://github.com/ollama/ollama/blob/main/docs/api.md
 */
interface OllamaRestApi {

    /**
     * https://github.com/ollama/ollama/blob/main/docs/api.md#list-local-models
     */
    suspend fun tags(): TagsResponse

    /**
     * https://github.com/ollama/ollama/blob/main/docs/api.md#version
     */
    suspend fun version(): VersionResponse

    /**
     * https://github.com/ollama/ollama/blob/main/docs/api.md#generate
     */
    suspend fun generate(request: GenerateRequest): GenerateResponse

    /**
     * https://github.com/ollama/ollama/blob/main/docs/api.md#show-model-information
     */
    suspend fun show(request: ShowRequest): ShowResponse
}