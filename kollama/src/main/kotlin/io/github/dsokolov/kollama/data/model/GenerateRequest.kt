package io.github.dsokolov.kollama.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerateRequest(
    @SerialName("model") val model: String,
    @SerialName("prompt") val prompt: String,
    @SerialName("stream") val stream: Boolean,
    @SerialName("options") val options: Options? = null,
) {

    @Serializable
    data class Options(
        @SerialName("seed") val seed: Int? = null,
    )
}
