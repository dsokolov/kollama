package me.ilich.kollama.data.mapper

import me.ilich.kollama.data.model.ShowRequest
import me.ilich.kollama.data.model.ShowResponse
import me.ilich.kollama.data.model.TagsResponse
import me.ilich.kollama.data.model.VersionResponse
import me.ilich.kollama.data.model.GenerateRequest
import me.ilich.kollama.data.model.GenerateResponse
import me.ilich.kollama.domain.model.OllamaModelShort
import me.ilich.kollama.domain.model.OllamaModelDetails
import me.ilich.kollama.domain.model.OllamaModelName
import me.ilich.kollama.domain.model.OllamaVersion
import me.ilich.kollama.domain.model.OllamaGeneration
import me.ilich.kollama.me.ilich.kollama.data.model.ChatRequest
import me.ilich.kollama.me.ilich.kollama.data.model.ChatResponse
import me.ilich.kollama.me.ilich.kollama.domain.model.OllamaMessage
import me.ilich.kollama.me.ilich.kollama.domain.model.OllamaMessageRole

internal class OllamaMapperImpl : OllamaMapper {

    override fun map(versionResponse: VersionResponse): OllamaVersion =
        versionResponse.version.let(::OllamaVersion)

    override fun map(versionResponse: TagsResponse): List<OllamaModelShort> =
        versionResponse.models.map { model ->
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
            options = if (seed != null) {
                GenerateRequest.Options(
                    seed = seed
                )
            } else {
                null
            }
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
                    role = message.role.let(::mapRoleToString),
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

    private fun mapStringToRole(s: String): OllamaMessageRole =
        when (s) {
            "tool" -> OllamaMessageRole.Tool
            "user" -> OllamaMessageRole.User
            "assistant" -> OllamaMessageRole.Assistant
            "system" -> OllamaMessageRole.System
            else -> OllamaMessageRole.Other(s)
        }

    override fun mapChatResponse(response: ChatResponse): OllamaMessage =
        OllamaMessage(
            role = response.message.role.let(::mapStringToRole),
            content = response.message.content
        )
}
