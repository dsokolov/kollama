package io.github.dsokolov.kollama.data.mapper

import io.github.dsokolov.kollama.data.model.ChatRequest
import io.github.dsokolov.kollama.data.model.ChatResponse
import io.github.dsokolov.kollama.data.model.GenerateRequest
import io.github.dsokolov.kollama.data.model.GenerateResponse
import io.github.dsokolov.kollama.data.model.ShowRequest
import io.github.dsokolov.kollama.data.model.ShowResponse
import io.github.dsokolov.kollama.data.model.TagsResponse
import io.github.dsokolov.kollama.data.model.VersionResponse
import io.github.dsokolov.kollama.domain.model.OllamaGeneration
import io.github.dsokolov.kollama.domain.model.OllamaMessage
import io.github.dsokolov.kollama.domain.model.OllamaModelDetails
import io.github.dsokolov.kollama.domain.model.OllamaModelName
import io.github.dsokolov.kollama.domain.model.OllamaModelShort
import io.github.dsokolov.kollama.domain.model.OllamaVersion

/**
 * Interface for mapping between data layer models and domain models
 */
interface OllamaMapper {

    fun map(versionResponse: VersionResponse): OllamaVersion

    fun map(tagsResponse: TagsResponse): List<OllamaModelShort>

    fun map(name: OllamaModelName, verbose: Boolean? = null): ShowRequest

    fun map(showResponse: ShowResponse): OllamaModelDetails

    fun mapGenerateRequest(
        model: OllamaModelName,
        prompt: String,
        stream: Boolean,
        seed: Int? = null,
    ): GenerateRequest

    fun mapGenerateResponse(response: GenerateResponse): OllamaGeneration
    
    fun mapChatRequest(
        model: OllamaModelName,
        messages: List<OllamaMessage>,
        stream: Boolean,
    ): ChatRequest

    fun mapChatResponse(response: ChatResponse): OllamaMessage
}
