package me.ilich.kollama.data

import me.ilich.kollama.data.api.OllamaRestApi
import me.ilich.kollama.data.mapper.OllamaMapper
import me.ilich.kollama.domain.OllamaClient
import me.ilich.kollama.domain.model.OllamaGeneration
import me.ilich.kollama.domain.model.OllamaMessage
import me.ilich.kollama.domain.model.OllamaModelDetails
import me.ilich.kollama.domain.model.OllamaModelName
import me.ilich.kollama.domain.model.OllamaModelShort
import me.ilich.kollama.domain.model.OllamaVersion

/**
 * Implementation of OllamaClient that handles communication with Ollama server
 * 
 * @param ollamaRestApi The REST API client for Ollama
 * @param ollamaMapper The mapper for converting between data and domain models
 */
internal class OllamaClientImpl(
    private val ollamaRestApi: OllamaRestApi,
    private val ollamaMapper: OllamaMapper,
) : OllamaClient {

    override suspend fun version(): OllamaVersion {
        return try {
            val versionResponse = ollamaRestApi.version()
            ollamaMapper.map(versionResponse)
        } catch (e: Exception) {
            throw OllamaException("Failed to get version", e)
        }
    }

    override suspend fun models(): List<OllamaModelShort> {
        return try {
            val tagsResponse = ollamaRestApi.tags()
            ollamaMapper.map(tagsResponse)
        } catch (e: Exception) {
            throw OllamaException("Failed to get models", e)
        }
    }

    override suspend fun model(modelName: OllamaModelName, verbose: Boolean?): OllamaModelDetails {
        return try {
            val showRequest = ollamaMapper.map(modelName, verbose)
            val showResponse = ollamaRestApi.show(showRequest)
            ollamaMapper.map(showResponse)
        } catch (e: Exception) {
            throw OllamaException("Failed to get model details for ${modelName.model}", e)
        }
    }

    override suspend fun generate(
        model: OllamaModelName,
        prompt: String,
        seed: Int?,
    ): OllamaGeneration {
        return try {
            val request = ollamaMapper.mapGenerateRequest(
                model = model,
                prompt = prompt,
                stream = false,
                seed = seed,
            )
            val response = ollamaRestApi.generate(request)
            ollamaMapper.mapGenerateResponse(response)
        } catch (e: Exception) {
            throw OllamaException("Failed to generate text for model ${model.model}", e)
        }
    }

    override suspend fun chat(model: OllamaModelName, messages: List<OllamaMessage>): OllamaMessage {
        return try {
            val request = ollamaMapper.mapChatRequest(
                model = model,
                messages = messages,
                stream = false
            )
            val response = ollamaRestApi.chat(request)
            ollamaMapper.mapChatResponse(response)
        } catch (e: Exception) {
            throw OllamaException("Failed to chat with model ${model.model}", e)
        }
    }
    
    override suspend fun pull(modelName: String, insecure: Boolean) {
        try {
            val request = me.ilich.kollama.data.model.PullRequest(
                name = modelName,
                insecure = insecure
            )
            ollamaRestApi.pull(request)
        } catch (e: Exception) {
            throw OllamaException("Failed to pull model $modelName", e)
        }
    }
    
    override suspend fun push(modelName: String, insecure: Boolean) {
        try {
            val request = me.ilich.kollama.data.model.PushRequest(
                name = modelName,
                insecure = insecure
            )
            ollamaRestApi.push(request)
        } catch (e: Exception) {
            throw OllamaException("Failed to push model $modelName", e)
        }
    }
    
    override suspend fun create(name: String, modelfile: String, path: String?) {
        try {
            val request = me.ilich.kollama.data.model.CreateRequest(
                name = name,
                modelfile = modelfile,
                path = path
            )
            ollamaRestApi.create(request)
        } catch (e: Exception) {
            throw OllamaException("Failed to create model $name", e)
        }
    }
    
    override suspend fun copy(source: String, destination: String) {
        try {
            val request = me.ilich.kollama.data.model.CopyRequest(
                source = source,
                destination = destination
            )
            ollamaRestApi.copy(request)
        } catch (e: Exception) {
            throw OllamaException("Failed to copy model from $source to $destination", e)
        }
    }
    
    override suspend fun delete(modelName: String) {
        try {
            val request = me.ilich.kollama.data.model.DeleteRequest(
                name = modelName
            )
            ollamaRestApi.delete(request)
        } catch (e: Exception) {
            throw OllamaException("Failed to delete model $modelName", e)
        }
    }
    
    override suspend fun embeddings(model: String, prompt: String): List<Double> {
        return try {
            val request = me.ilich.kollama.data.model.EmbeddingsRequest(
                model = model,
                prompt = prompt
            )
            val response = ollamaRestApi.embeddings(request)
            response.embeddings
        } catch (e: Exception) {
            throw OllamaException("Failed to generate embeddings for model $model", e)
        }
    }
}

/**
 * Custom exception for Ollama-related errors
 */
class OllamaException(message: String, cause: Throwable? = null) : Exception(message, cause)