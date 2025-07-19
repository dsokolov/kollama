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
import io.github.dsokolov.kollama.domain.model.OllamaMessageRole
import io.github.dsokolov.kollama.domain.model.OllamaModelDetails
import io.github.dsokolov.kollama.domain.model.OllamaModelName
import io.github.dsokolov.kollama.domain.model.OllamaModelShort
import io.github.dsokolov.kollama.domain.model.OllamaVersion

/**
 * Implementation of OllamaMapper that handles conversion between data and domain models
 */
internal class OllamaMapperImpl : OllamaMapper {

    override fun map(versionResponse: VersionResponse): OllamaVersion =
        OllamaVersion(versionResponse.version)

    override fun map(tagsResponse: TagsResponse): List<OllamaModelShort> =
        tagsResponse.models.map { model ->
            OllamaModelShort(
                name = OllamaModelName(model.name),
            )
        }

    override fun map(name: OllamaModelName, verbose: Boolean?): ShowRequest =
        ShowRequest(
            model = name.model,
            verbose = verbose,
        )

    override fun map(showResponse: ShowResponse): OllamaModelDetails =
        OllamaModelDetails(
            modelFile = showResponse.modelfile,
            parameters = showResponse.parameters,
        )

    override fun mapGenerateRequest(
        model: OllamaModelName,
        prompt: String,
        stream: Boolean,
        seed: Int?
    ): GenerateRequest =
        GenerateRequest(
            model = model.model,
            prompt = prompt,
            stream = stream,
            options = seed?.let { GenerateRequest.Options(seed = it) }
        )

    override fun mapGenerateResponse(response: GenerateResponse): OllamaGeneration =
        OllamaGeneration.Standard(
            response = response.response
        )

    override fun mapChatRequest(
        model: OllamaModelName,
        messages: List<OllamaMessage>,
        stream: Boolean
    ): ChatRequest =
        ChatRequest(
            model = model.model,
            stream = stream,
            messages = messages.map { message ->
                ChatRequest.Message(
                    role = mapRoleToString(message.role),
                    content = message.content
                )
            }
        )

    private fun mapRoleToString(role: OllamaMessageRole): String =
        when (role) {
            OllamaMessageRole.Tool -> "tool"
            OllamaMessageRole.User -> "user"
            OllamaMessageRole.Assistant -> "assistant"
            OllamaMessageRole.System -> "system"
            is OllamaMessageRole.Other -> role.role
        }

    private fun mapStringToRole(roleString: String): OllamaMessageRole =
        when (roleString) {
            "tool" -> OllamaMessageRole.Tool
            "user" -> OllamaMessageRole.User
            "assistant" -> OllamaMessageRole.Assistant
            "system" -> OllamaMessageRole.System
            else -> OllamaMessageRole.Other(roleString)
        }

    override fun mapChatResponse(response: ChatResponse): OllamaMessage =
        OllamaMessage(
            role = mapStringToRole(response.message.role),
            content = response.message.content
        )
}
