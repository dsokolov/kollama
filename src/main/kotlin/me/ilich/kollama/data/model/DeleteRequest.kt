package me.ilich.kollama.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/**
 * Request model for deleting a model
 * https://github.com/ollama/ollama/blob/main/docs/api.md#delete-a-model
 */
@Serializable
data class DeleteRequest(
    @SerialName("name")
    val name: String
) 
