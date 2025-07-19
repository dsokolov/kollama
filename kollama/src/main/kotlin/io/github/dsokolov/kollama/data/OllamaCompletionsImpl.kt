package io.github.dsokolov.kollama.data

import io.github.dsokolov.kollama.data.OllamaException
import io.github.dsokolov.kollama.data.api.OllamaRestApi
import io.github.dsokolov.kollama.data.mapper.OllamaMapper
import io.github.dsokolov.kollama.domain.model.OllamaGeneration
import io.github.dsokolov.kollama.domain.model.OllamaMessage
import io.github.dsokolov.kollama.domain.model.OllamaModelName
import io.github.dsokolov.kollama.domain.OllamaCompletions

class OllamaCompletionsImpl(
    private val ollamaRestApi: OllamaRestApi,
    private val ollamaMapper: OllamaMapper,
) : OllamaCompletions {

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

    override suspend fun embeddings(model: String, prompt: String): List<Double> {
        return try {
            val request = io.github.dsokolov.kollama.data.model.EmbeddingsRequest(
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