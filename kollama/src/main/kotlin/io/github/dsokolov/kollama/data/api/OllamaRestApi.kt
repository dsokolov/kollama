package io.github.dsokolov.kollama.data.api

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

    /**
     * https://github.com/ollama/ollama/blob/main/docs/api.md#pull-a-model
     * Pull a model from the library
     */
    suspend fun pull(request: PullRequest): PullResponse

    /**
     * https://github.com/ollama/ollama/blob/main/docs/api.md#push-a-model
     * Push a model to the library
     */
    suspend fun push(request: PushRequest): PushResponse

    /**
     * https://github.com/ollama/ollama/blob/main/docs/api.md#create-a-model
     * Create a model from a Modelfile
     */
    suspend fun create(request: CreateRequest): CreateResponse

    /**
     * https://github.com/ollama/ollama/blob/main/docs/api.md#copy-a-model
     * Copy a model
     */
    suspend fun copy(request: CopyRequest): CopyResponse

    /**
     * https://github.com/ollama/ollama/blob/main/docs/api.md#delete-a-model
     * Delete a model
     */
    suspend fun delete(request: DeleteRequest): DeleteResponse

    /**
     * https://github.com/ollama/ollama/blob/main/docs/api.md#generate-embeddings
     * Generate embeddings from a model
     */
    suspend fun embeddings(request: EmbeddingsRequest): EmbeddingsResponse

}