package io.github.dsokolov.kollama.data

import io.github.dsokolov.kollama.data.api.OllamaRestApi
import io.github.dsokolov.kollama.data.mapper.OllamaMapper
import io.github.dsokolov.kollama.domain.OllamaClient
import io.github.dsokolov.kollama.domain.model.OllamaGeneration
import io.github.dsokolov.kollama.domain.model.OllamaMessage
import io.github.dsokolov.kollama.domain.model.OllamaModelDetails
import io.github.dsokolov.kollama.domain.model.OllamaModelName
import io.github.dsokolov.kollama.domain.model.OllamaModelShort
import io.github.dsokolov.kollama.domain.model.OllamaVersion
import io.github.dsokolov.kollama.data.OllamaCompletionsImpl
import io.github.dsokolov.kollama.data.OllamaManipulationsImpl

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
    ollamaCompletions = OllamaCompletionsImpl(
        ollamaRestApi = ollamaRestApi,
        ollamaMapper = ollamaMapper,
    ),
)

/**
 * Custom exception for Ollama-related errors
 */
class OllamaException(message: String, cause: Throwable? = null) : Exception(message, cause)