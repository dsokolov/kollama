package io.github.dsokolov.kollama.data

import io.github.dsokolov.kollama.data.api.OllamaRestApi
import io.github.dsokolov.kollama.data.mapper.OllamaMapper
import io.github.dsokolov.kollama.domain.OllamaClient
import io.github.dsokolov.kollama.domain.OllamaCompletions
import io.github.dsokolov.kollama.domain.model.OllamaModelName
import io.github.dsokolov.kollama.logger.Logger

/**
 * Implementation of OllamaClient that handles communication with Ollama server
 *
 * @param ollamaRestApi The REST API client for Ollama
 * @param ollamaMapper The mapper for converting between data and domain models
 */
internal class OllamaClientImpl(
    override val restApi: OllamaRestApi,
    override val mapper: OllamaMapper,
    override val logger: Logger?,
) : OllamaClient

/**
 * Custom exception for Ollama-related errors
 */
class OllamaException(message: String, cause: Throwable? = null) : Exception(message, cause)
