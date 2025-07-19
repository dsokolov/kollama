package io.github.dsokolov.kollama.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request model for embeddings API
 * 
 * @see https://github.com/ollama/ollama/blob/main/docs/api.md#generate-embeddings
 */
@Serializable
data class EmbeddingsRequest(
    @SerialName("model") val model: String,
    @SerialName("prompt") val prompt: String,
    @SerialName("options") val options: Map<String, String>? = null
)
