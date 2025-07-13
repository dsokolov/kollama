package me.ilich.kollama.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/**
 * Response model for pushing a model to the library
 * https://github.com/ollama/ollama/blob/main/docs/api.md#push-a-model
 */
@Serializable
data class PushResponse(
    @SerialName("status")
    val status: String
) 