package me.ilich.kollama.data.api.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerateRequest(
    @SerialName("model") val model: String,
    @SerialName("prompt") val prompt: String,
    @SerialName("suffix") val suffix: String? = null,
    @SerialName("images") val images: List<String>? = null,
    @SerialName("think") val think: Boolean? = null,
)
