package me.ilich.kollama.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerateResponse(
    @SerialName("response") val response: String,
    @SerialName("done") val done: Boolean,
)
