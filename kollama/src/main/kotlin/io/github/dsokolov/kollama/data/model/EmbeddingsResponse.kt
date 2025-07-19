package io.github.dsokolov.kollama.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response model for embeddings API
 * 
 * @see https://github.com/ollama/ollama/blob/main/docs/api.md#generate-embeddings
 */
@Serializable
data class EmbeddingsResponse(
    @SerialName("embeddings") val embeddings: List<Double>
)