package io.github.dsokolov.kollama

import java.net.URI
import io.github.dsokolov.kollama.data.OllamaClientImpl
import io.github.dsokolov.kollama.data.OllamaCompletionsImpl
import io.github.dsokolov.kollama.data.OllamaManipulationsImpl
import io.github.dsokolov.kollama.data.api.OllamaRestApiKtorImpl
import io.github.dsokolov.kollama.data.mapper.OllamaMapperImpl
import io.github.dsokolov.kollama.domain.OllamaClient
import io.github.dsokolov.kollama.domain.OllamaCompletions
import io.github.dsokolov.kollama.domain.OllamaManipulations
import io.github.dsokolov.kollama.domain.model.OllamaModelName
import io.github.dsokolov.kollama.logger.Logger
import io.github.dsokolov.kollama.logger.SilentLogger

/**
 * Default local Ollama server URI
 */
private val DEFAULT_OLLAMA_URI = URI.create("http://127.0.0.1:11434")

/**
 * Creates an OllamaClient instance with the specified server URI
 *
 * @param uri The URI of the Ollama server (defaults to localhost:11434)
 * @return Configured OllamaClient instance
 */
fun ollama(
    uri: URI = DEFAULT_OLLAMA_URI,
    logger: Logger? = null
): OllamaClient =
    OllamaClientImpl(
        restApi = OllamaRestApiKtorImpl(
            baseUri = uri,
            logger = logger,
        ),
        mapper = OllamaMapperImpl(),
        logger = logger,
    )

fun OllamaClient.completions(
    model: OllamaModelName,
    isStream: Boolean = false,
): OllamaCompletions {
    return OllamaCompletionsImpl(
        modelName = model,
        isStream = isStream,
        ollamaRestApi = restApi,
        ollamaMapper = mapper,
        logger = logger,
    )
}


fun OllamaClient.manipulations(): OllamaManipulations =
    OllamaManipulationsImpl(
        ollamaRestApi = restApi,
        ollamaMapper = mapper,
    )
