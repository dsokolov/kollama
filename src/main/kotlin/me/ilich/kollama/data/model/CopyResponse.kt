package me.ilich.kollama.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response model for copy model API
 * 
 * @see https://github.com/ollama/ollama/blob/main/docs/api.md#copy-a-model
 */
@Serializable
data class CopyResponse(
    @SerialName("status") val status: String
)
