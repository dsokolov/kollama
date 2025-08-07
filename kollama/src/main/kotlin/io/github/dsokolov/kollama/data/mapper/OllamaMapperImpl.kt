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
import io.github.dsokolov.kollama.domain.model.Message
import io.github.dsokolov.kollama.domain.model.MessageRole
import io.github.dsokolov.kollama.domain.model.OllamaModelDetails
import io.github.dsokolov.kollama.domain.model.OllamaModelName
import io.github.dsokolov.kollama.domain.model.OllamaModelShort
import io.github.dsokolov.kollama.domain.model.OllamaVersion
import io.github.dsokolov.kollama.domain.model.Seed

/**
 * Implementation of OllamaMapper that handles conversion between data and domain models
 */
internal class OllamaMapperImpl : OllamaMapper {

    override fun map(versionResponse: VersionResponse): OllamaVersion =
        OllamaVersion(versionResponse.version)

    override fun map(tagsResponse: TagsResponse): List<OllamaModelShort> =
        tagsResponse.models.map { model ->
            OllamaModelShort(
                name = model.name,
            )
        }

    override fun map(name: OllamaModelName, verbose: Boolean?): ShowRequest =
        ShowRequest(
            model = name,
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
            model = model,
            prompt = prompt,
            stream = stream,
            options = seed?.let { GenerateRequest.Options(seed = it) }
        )

    override fun mapGenerateResponse(response: GenerateResponse): OllamaGeneration =
        OllamaGeneration(
            response = response.response
        )

    override fun mapChatRequest(
        model: OllamaModelName,
        messages: List<Message>,
        stream: Boolean,
        seed: Seed?,
    ): ChatRequest =
        ChatRequest(
            model = model,
            stream = stream,
            messages = messages.map { message ->
                ChatRequest.Message(
                    role = mapRoleToString(message.role),
                    content = message.content
                )
            },
            options = mapChatOptions(seed),
        )

    private fun mapChatOptions(seed: Seed?): ChatRequest.Options? {
        return if (seed == null) {
            null
        } else {
            ChatRequest.Options(
                seed = seed
            )
        }
    }

    private fun mapRoleToString(role: MessageRole): String =
        when (role) {
            MessageRole.Tool -> "tool"
            MessageRole.User -> "user"
            MessageRole.Assistant -> "assistant"
            MessageRole.System -> "system"
            is MessageRole.Other -> role.role
        }

    private fun mapStringToRole(roleString: String): MessageRole =
        when (roleString) {
            "tool" -> MessageRole.Tool
            "user" -> MessageRole.User
            "assistant" -> MessageRole.Assistant
            "system" -> MessageRole.System
            else -> MessageRole.Other(roleString)
        }

    override fun mapChatResponse(response: ChatResponse): Message =
        Message(
            role = mapStringToRole(response.message.role),
            content = response.message.content
        )
}
