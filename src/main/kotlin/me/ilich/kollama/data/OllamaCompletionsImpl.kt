package me.ilich.kollama.me.ilich.kollama.data

import me.ilich.kollama.data.OllamaException
import me.ilich.kollama.data.api.OllamaRestApi
import me.ilich.kollama.data.mapper.OllamaMapper
import me.ilich.kollama.domain.model.OllamaGeneration
import me.ilich.kollama.domain.model.OllamaMessage
import me.ilich.kollama.domain.model.OllamaModelName
import me.ilich.kollama.me.ilich.kollama.domain.OllamaCompletions

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