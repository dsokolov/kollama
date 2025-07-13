package me.ilich.kollama.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/**
 * Request model for pulling a model from the library
 * https://github.com/ollama/ollama/blob/main/docs/api.md#pull-a-model
 */
@Serializable
data class PullRequest(
    @SerialName("name")
    val name: String,
    @SerialName("insecure")
    val insecure: Boolean = false
) 