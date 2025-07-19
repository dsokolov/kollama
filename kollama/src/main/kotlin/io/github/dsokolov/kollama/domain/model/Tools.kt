package io.github.dsokolov.kollama.domain.model


@JvmInline
value class ToolName(val toolName: String)

@JvmInline
value class ArgumentName(val argumentName: String)

@JvmInline
value class ArgumentValue(val argumentValue: String)

data class ToolCall(
    val name: ToolName,
    val arguments: Map<ArgumentName, ArgumentValue>
)
