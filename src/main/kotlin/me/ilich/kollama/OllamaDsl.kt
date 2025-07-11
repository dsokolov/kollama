package me.ilich.kollama.me.ilich.kollama

import me.ilich.kollama.Ollama
import me.ilich.kollama.api.OllamaRestApiKtorImpl
import java.net.URI

private val OLLAMA_LOCAL = URI.create("http://127.0.0.1:11434")

fun ollama(
    uri: URI = OLLAMA_LOCAL
) : Ollama =
    Ollama(
        ollamaRestApi = OllamaRestApiKtorImpl(uri),
        ollamaMapper = OllamaMapperImpl(),
    )