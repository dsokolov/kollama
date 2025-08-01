package io.github.dsokolov.kollama.domain

import io.github.dsokolov.kollama.data.api.OllamaRestApi
import io.github.dsokolov.kollama.data.mapper.OllamaMapper
import io.github.dsokolov.kollama.domain.model.OllamaModelName
import io.github.dsokolov.kollama.logger.Logger

/**
 * Client interface for interacting with Ollama API
 *
 * This interface provides methods to interact with the Ollama server,
 * including model management, text generation, and chat functionality.
 */
interface OllamaClient {
    val restApi: OllamaRestApi
    val mapper: OllamaMapper
    val logger: Logger?
}
