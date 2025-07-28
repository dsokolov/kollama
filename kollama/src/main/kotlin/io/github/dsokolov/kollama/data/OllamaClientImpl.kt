package io.github.dsokolov.kollama.data

import io.github.dsokolov.kollama.data.api.OllamaRestApi
import io.github.dsokolov.kollama.data.mapper.OllamaMapper
import io.github.dsokolov.kollama.domain.OllamaClient
import io.github.dsokolov.kollama.domain.OllamaCompletions
import io.github.dsokolov.kollama.domain.model.OllamaModelName

/**
 * Implementation of OllamaClient that handles communication with Ollama server
 *
 * @param ollamaRestApi The REST API client for Ollama
 * @param ollamaMapper The mapper for converting between data and domain models
 */
internal class OllamaClientImpl(
    private val ollamaRestApi: OllamaRestApi,
    private val ollamaMapper: OllamaMapper,
) : OllamaClient(
    ollamaManipulations = OllamaManipulationsImpl(
        ollamaRestApi = ollamaRestApi,
        ollamaMapper = ollamaMapper,
    ),
) {
    override fun getCompletions(
        model: OllamaModelName,
        isStream: Boolean,
    ): OllamaCompletions {
        return OllamaCompletionsImpl(
            modelName = model,
            isStream = isStream,
            ollamaRestApi = ollamaRestApi,
            ollamaMapper = ollamaMapper,
        )
    }
}

/**
 * Custom exception for Ollama-related errors
 */
class OllamaException(message: String, cause: Throwable? = null) : Exception(message, cause)