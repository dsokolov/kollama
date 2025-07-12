package me.ilich.kollama.data.api.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://github.com/ollama/ollama/blob/main/docs/api.md#list-local-models
 */
@Serializable
data class TagsResponse(
    @SerialName("models") val models: List<Model>
) {
    @Serializable
    data class Model(
        @SerialName("name") val name: String,
        @SerialName("modified_at") val modifiedAt: String,
        @SerialName("size") val size: Long,
        @SerialName("digest") val digest: String,
        @SerialName("details") val details: Details
    )

    @Serializable
    data class Details(
        @SerialName("format") val format: String,
        @SerialName("family") val family: String,
        @SerialName("families") val families: List<String>?,
        @SerialName("parameter_size") val parameterSize: String?,
        @SerialName("quantization_level") val quantizationLevel: String?
    )
}
