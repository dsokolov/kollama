package me.ilich.kollama.data.api

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