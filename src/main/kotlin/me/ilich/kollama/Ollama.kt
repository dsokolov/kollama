package me.ilich.kollama

import java.net.URI
import me.ilich.kollama.data.OllamaClientImpl
import me.ilich.kollama.data.api.OllamaRestApiKtorImpl
import me.ilich.kollama.data.mapper.OllamaMapperImpl
import me.ilich.kollama.domain.OllamaClient

private val OLLAMA_LOCAL = URI.create("http://127.0.0.1:11434")

fun ollama(
    uri: URI = OLLAMA_LOCAL
) : OllamaClient =
    OllamaClientImpl(
        ollamaRestApi = OllamaRestApiKtorImpl(uri),
        ollamaMapper = OllamaMapperImpl(),
    )
