package me.ilich.kollama.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/**
 * Response model for creating a model
 * https://github.com/ollama/ollama/blob/main/docs/api.md#create-a-model
 */
@Serializable
data class CreateResponse(
    @SerialName("status")
    val status: String
) 