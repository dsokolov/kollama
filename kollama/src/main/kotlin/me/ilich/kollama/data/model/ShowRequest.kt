package me.ilich.kollama.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://github.com/ollama/ollama/blob/main/docs/api.md#show-model-information
 */
@Serializable
data class ShowRequest(
    @SerialName("model") val model: String,
    @SerialName("verbose") val verbose: Boolean? = null
)
