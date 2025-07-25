package io.github.dsokolov.kollama.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VersionResponse(
    @SerialName("version") val version: String
)
