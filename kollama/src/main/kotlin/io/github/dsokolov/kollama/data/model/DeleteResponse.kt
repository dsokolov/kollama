package io.github.dsokolov.kollama.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response model for delete model API
 * 
 * @see https://github.com/ollama/ollama/blob/main/docs/api.md#delete-a-model
 */
@Serializable
data class DeleteResponse(
    @SerialName("status") val status: String
)