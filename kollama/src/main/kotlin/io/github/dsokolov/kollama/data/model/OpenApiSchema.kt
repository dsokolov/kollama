package io.github.dsokolov.kollama.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias OpenApiType = String
typealias OpenApiName = String

@Serializable
data class OpenApiItem(
    @SerialName("type") val type: OpenApiType? = null,
    @SerialName("name") val name: OpenApiName? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("function") val function: OpenApiItem? = null,
    @SerialName("parameters") val parameters: OpenApiItem? = null,
    @SerialName("properties") val properties: Map<OpenApiName, OpenApiItem>? = null,
    @SerialName("required") val required: List<OpenApiName>? = null,
)
