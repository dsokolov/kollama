package me.ilich.kollama.api

import me.ilich.kollama.api.data.GenerateRequest
import me.ilich.kollama.api.data.GenerateResponse
import me.ilich.kollama.api.data.TagsResponse
import me.ilich.kollama.api.data.VersionResponse

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

}