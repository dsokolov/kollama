package me.ilich.kollama.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/**
 * Request model for pushing a model to the library
 * https://github.com/ollama/ollama/blob/main/docs/api.md#push-a-model
 */
@Serializable
data class PushRequest(
    @SerialName("name")
    val name: String,
    @SerialName("insecure")
    val insecure: Boolean = false
) 