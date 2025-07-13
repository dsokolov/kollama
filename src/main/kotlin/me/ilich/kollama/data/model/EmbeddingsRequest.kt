package me.ilich.kollama.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/**
 * Request model for generating embeddings
 * https://github.com/ollama/ollama/blob/main/docs/api.md#generate-embeddings
 */
@Serializable
data class EmbeddingsRequest(
    @SerialName("model")
    val model: String,
    @SerialName("prompt")
    val prompt: String,
    @SerialName("options")
    val options: Map<String, String>? = null
)
