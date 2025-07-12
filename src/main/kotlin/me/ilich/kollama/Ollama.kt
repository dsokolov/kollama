package me.ilich.kollama

import me.ilich.kollama.data.OllamaClientImpl
import me.ilich.kollama.data.api.OllamaRestApiKtorImpl
import me.ilich.kollama.data.mapper.OllamaMapperImpl
import me.ilich.kollama.domain.OllamaClient
import java.net.URI

private val OLLAMA_LOCAL = URI.create("http://127.0.0.1:11434")

fun ollama(
    uri: URI = OLLAMA_LOCAL
) : OllamaClient =
    OllamaClientImpl(
        ollamaRestApi = OllamaRestApiKtorImpl(uri),
        ollamaMapper = OllamaMapperImpl(),
    )
