package io.github.dsokolov.kollama.data

import io.github.dsokolov.kollama.data.api.OllamaRestApi
import io.github.dsokolov.kollama.data.mapper.OllamaMapper
import io.github.dsokolov.kollama.domain.model.OllamaGeneration
import io.github.dsokolov.kollama.domain.model.Message
import io.github.dsokolov.kollama.domain.model.OllamaModelName
import io.github.dsokolov.kollama.domain.OllamaCompletions
import io.github.dsokolov.kollama.domain.model.History
import io.github.dsokolov.kollama.domain.model.Seed
import io.github.dsokolov.kollama.domain.model.Tool
import io.github.dsokolov.kollama.logger.Logger

class OllamaCompletionsImpl(
    private val modelName: OllamaModelName,
    private val isStream: Boolean,
    private val ollamaRestApi: OllamaRestApi,
    private val ollamaMapper: OllamaMapper,
    private val logger: Logger?,
) : OllamaCompletions {

    override suspend fun generate(
        prompt: String,
        seed: Int?,
    ): OllamaGeneration {
        return try {
            val request = ollamaMapper.mapGenerateRequest(
                model = modelName,
                prompt = prompt,
                stream = isStream,
                seed = seed,
            )
            val response = ollamaRestApi.generate(request)
            ollamaMapper.mapGenerateResponse(response)
        } catch (e: Exception) {
            throw OllamaException("Failed to generate text for model $modelName", e)
        }
    }

    override suspend fun chat(
        history: History,
        message: Message?,
        seed: Seed?,
        tools: List<Tool>?,
    ): Message {
        val messages = when (message) {
            null -> history.messages
            else -> history.messages + message
        }
        return try {
            val request = ollamaMapper.mapChatRequest(
                model = modelName,
                messages = messages,
                stream = isStream,
                seed = seed,
                tools = tools,
            )
            val response = ollamaRestApi.chat(request)
            ollamaMapper.mapChatResponse(response)
        } catch (e: Exception) {
            throw OllamaException("Failed to chat with model $modelName", e)
        }
    }

    override suspend fun embeddings(prompt: String): List<Double> {
        return try {
            val request = io.github.dsokolov.kollama.data.model.EmbeddingsRequest(
                model = modelName,
                prompt = prompt
            )
            val response = ollamaRestApi.embeddings(request)
            response.embeddings
        } catch (e: Exception) {
            throw OllamaException("Failed to generate embeddings for model $modelName", e)
        }
    }
}
