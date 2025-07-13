package me.ilich.kollama.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/**
 * Response model for copying a model
 * https://github.com/ollama/ollama/blob/main/docs/api.md#copy-a-model
 */
@Serializable
data class CopyResponse(
    @SerialName("status")
    val status: String
)
