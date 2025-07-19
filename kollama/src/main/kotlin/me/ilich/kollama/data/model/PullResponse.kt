package me.ilich.kollama.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response model for pull model API
 * 
 * @see https://github.com/ollama/ollama/blob/main/docs/api.md#pull-a-model
 */
@Serializable
data class PullResponse(
    @SerialName("status") val status: String
)