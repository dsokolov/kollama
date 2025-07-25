package io.github.dsokolov.kollama

import java.net.URI
import io.github.dsokolov.kollama.data.OllamaClientImpl
import io.github.dsokolov.kollama.data.api.OllamaRestApiKtorImpl
import io.github.dsokolov.kollama.data.mapper.OllamaMapperImpl
import io.github.dsokolov.kollama.domain.OllamaClient

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

): OllamaClient =
    OllamaClientImpl(
        ollamaRestApi = OllamaRestApiKtorImpl(uri),
        ollamaMapper = OllamaMapperImpl(),
    )
