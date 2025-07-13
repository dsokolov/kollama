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
import me.ilich.kollama.me.ilich.kollama.data.OllamaCompletionsImpl
import me.ilich.kollama.me.ilich.kollama.data.OllamaManipulationsImpl

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