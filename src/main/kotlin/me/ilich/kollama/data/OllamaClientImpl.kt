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

internal class OllamaClientImpl(
    private val ollamaRestApi: OllamaRestApi,
    private val ollamaMapper: OllamaMapper,
) : OllamaClient {

    override suspend fun version(): OllamaVersion {
        val versionResponse = ollamaRestApi.version()
        val version = ollamaMapper.map(versionResponse)
        return version
    }

    override suspend fun models(): List<OllamaModelShort> {
        val tagsResponse = ollamaRestApi.tags()
        return ollamaMapper.map(tagsResponse)
    }

    override suspend fun model(modelName: OllamaModelName, verbose: Boolean?): OllamaModelDetails {
        val showRequest = ollamaMapper.map(modelName, verbose)
        val showResponse = ollamaRestApi.show(showRequest)
        return ollamaMapper.map(showResponse)
    }

    override suspend fun generate(
        model: OllamaModelName,
        prompt: String,
        seed: Int?,
    ): OllamaGeneration {
        val request = ollamaMapper.mapGenerateRequest(
            model = model,
            prompt = prompt,
            stream = false,
            seed = seed,
        )
        val response = ollamaRestApi.generate(request)
        return ollamaMapper.mapGenerateResponse(response)
    }

    override suspend fun chat(model: OllamaModelName, messages: List<OllamaMessage>): OllamaMessage {
        val request = ollamaMapper.mapChatRequest(
            model = model,
            messages = messages,
            stream = false
        )
        val response = ollamaRestApi.chat(request)
        return ollamaMapper.mapChatResponse(response)
    }
}