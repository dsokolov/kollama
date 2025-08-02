package io.github.dsokolov.kollama.domain.model

data class Message(
    val role: MessageRole,
    val content: MessageContent,
    val toolCalls: List<ToolCall>? = null,
    val toolName: ToolName? = null,
)


sealed class MessageRole {

    data object System : MessageRole()

    data object User : MessageRole()

    data object Assistant : MessageRole()

    data object Tool : MessageRole()

    data class Other(val role: String) : MessageRole()
}

typealias MessageContent = String

fun assistant(content: MessageContent) =
    Message(
        role = MessageRole.Assistant,
        content = content,
    )