package io.github.dsokolov.kollama.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response model for create model API
 * 
 * @see https://github.com/ollama/ollama/blob/main/docs/api.md#create-a-model
 */
@Serializable
data class CreateResponse(
    @SerialName("status") val status: String
)