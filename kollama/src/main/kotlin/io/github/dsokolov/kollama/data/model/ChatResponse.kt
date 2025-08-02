package io.github.dsokolov.kollama.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatResponse(
    @SerialName("model") val model: String,
    @SerialName("message") val message: Message,
) {

    @Serializable
    data class Message(
        @SerialName("role") val role: String,
        @SerialName("content") val content: String,
        @SerialName("tool_calls") val toolCalls: List<ToolCall>? = null,
    ) {

        @Serializable
        data class ToolCall(
            @SerialName("function") val function: Function,
        ) {

            @Serializable
            data class Function(
                @SerialName("name") val name: String,
                @SerialName("arguments") val arguments: Map<String, String>,
            )
        }
    }
}
