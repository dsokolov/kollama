package me.ilich.kollama.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request model for push model API
 * 
 * @see https://github.com/ollama/ollama/blob/main/docs/api.md#push-a-model
 */
@Serializable
data class PushRequest(
    @SerialName("name") val name: String,
    @SerialName("insecure") val insecure: Boolean? = null
)