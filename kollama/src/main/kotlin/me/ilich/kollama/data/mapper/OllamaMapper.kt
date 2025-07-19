package me.ilich.kollama.data.mapper

import me.ilich.kollama.data.model.ChatRequest
import me.ilich.kollama.data.model.ChatResponse
import me.ilich.kollama.data.model.GenerateRequest
import me.ilich.kollama.data.model.GenerateResponse
import me.ilich.kollama.data.model.ShowRequest
import me.ilich.kollama.data.model.ShowResponse
import me.ilich.kollama.data.model.TagsResponse
import me.ilich.kollama.data.model.VersionResponse
import me.ilich.kollama.domain.model.OllamaGeneration
import me.ilich.kollama.domain.model.OllamaMessage
import me.ilich.kollama.domain.model.OllamaModelDetails
import me.ilich.kollama.domain.model.OllamaModelName
import me.ilich.kollama.domain.model.OllamaModelShort
import me.ilich.kollama.domain.model.OllamaVersion

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
