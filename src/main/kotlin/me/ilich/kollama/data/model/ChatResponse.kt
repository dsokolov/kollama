package me.ilich.kollama.me.ilich.kollama.data.model

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
    )
}
