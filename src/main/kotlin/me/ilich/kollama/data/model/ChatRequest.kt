package me.ilich.kollama.me.ilich.kollama.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatRequest(
    @SerialName("model") val model: String,
    @SerialName("messages") val messages: List<Message>,
    @SerialName("stream") val stream: Boolean,
) {

    @Serializable
    data class Message(
        @SerialName("role") val role: String,
        @SerialName("content") val content: String
    )
}
