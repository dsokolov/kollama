package io.github.dsokolov.kollama.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request model for create model API
 * 
 * @see https://github.com/ollama/ollama/blob/main/docs/api.md#create-a-model
 */
@Serializable
data class CreateRequest(
    @SerialName("name") val name: String,
    @SerialName("modelfile") val modelfile: String,
    @SerialName("path") val path: String? = null
)