package me.ilich.kollama.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/**
 * Request model for creating a model
 * https://github.com/ollama/ollama/blob/main/docs/api.md#create-a-model
 */
@Serializable
data class CreateRequest(
    @SerialName("name")
    val name: String,
    @SerialName("modelfile")
    val modelfile: String,
    @SerialName("path")
    val path: String? = null
) 