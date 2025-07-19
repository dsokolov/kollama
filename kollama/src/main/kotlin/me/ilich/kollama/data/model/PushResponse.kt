package me.ilich.kollama.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response model for push model API
 * 
 * @see https://github.com/ollama/ollama/blob/main/docs/api.md#push-a-model
 */
@Serializable
data class PushResponse(
    @SerialName("status") val status: String
)