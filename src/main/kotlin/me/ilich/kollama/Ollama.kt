package me.ilich.kollama

import me.ilich.kollama.api.OllamaRestApi
import me.ilich.kollama.me.ilich.kollama.OllamaMapper
import me.ilich.kollama.me.ilich.kollama.domain.OllamaModel
import me.ilich.kollama.me.ilich.kollama.domain.OllamaModelFull
import me.ilich.kollama.me.ilich.kollama.domain.OllamaModelName
import me.ilich.kollama.me.ilich.kollama.domain.OllamaVersion

class Ollama(
    private val ollamaRestApi: OllamaRestApi,
    private val ollamaMapper: OllamaMapper,
) {

    suspend fun version(): OllamaVersion {
        val versionResponse = ollamaRestApi.version()
        val version = ollamaMapper.map(versionResponse)
        return version
    }

    suspend fun models(): List<OllamaModel> {
        val tagsResponse = ollamaRestApi.tags()
        return ollamaMapper.map(tagsResponse)
    }

    suspend fun model(name: OllamaModelName) : OllamaModelFull {
        val showRequest = ollamaMapper.map(name)
        val showResponse = ollamaRestApi.show(showRequest)
        return ollamaMapper.map(showResponse)
    }
}