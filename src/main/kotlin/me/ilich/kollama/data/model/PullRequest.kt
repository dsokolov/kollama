package me.ilich.kollama.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request model for pull model API
 * 
 * @see https://github.com/ollama/ollama/blob/main/docs/api.md#pull-a-model
 */
@Serializable
data class PullRequest(
    @SerialName("name") val name: String,
    @SerialName("insecure") val insecure: Boolean? = null
)