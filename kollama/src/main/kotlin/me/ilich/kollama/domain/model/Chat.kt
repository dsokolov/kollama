package me.ilich.kollama.domain.model

import me.ilich.kollama.domain.model.ToolCall
import me.ilich.kollama.domain.model.ToolName

sealed class OllamaMessageRole {

    data object System : OllamaMessageRole()

    data object User : OllamaMessageRole()

    data object Assistant : OllamaMessageRole()

    data object Tool : OllamaMessageRole()

    data class Other(val role: String) : OllamaMessageRole()
}

data class OllamaMessage(
    val role: OllamaMessageRole,
    val content: String,
    val toolCalls: List<ToolCall>? = null,
    val toolName: ToolName? = null,
)
