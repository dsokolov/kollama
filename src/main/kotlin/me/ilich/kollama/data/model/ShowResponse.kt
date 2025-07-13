package me.ilich.kollama.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response model for show model information API
 * 
 * @see https://github.com/ollama/ollama/blob/main/docs/api.md#show-model-information
 */
@Serializable
data class ShowResponse(
    @SerialName("modelfile") val modelfile: String? = null,
    @SerialName("parameters") val parameters: String? = null,
)