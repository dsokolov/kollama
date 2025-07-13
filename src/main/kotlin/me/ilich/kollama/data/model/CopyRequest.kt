package me.ilich.kollama.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/**
 * Request model for copying a model
 * https://github.com/ollama/ollama/blob/main/docs/api.md#copy-a-model
 */
@Serializable
data class CopyRequest(
    @SerialName("source")
    val source: String,
    @SerialName("destination")
    val destination: String
) 