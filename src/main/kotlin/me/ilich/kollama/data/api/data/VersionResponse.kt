package me.ilich.kollama.data.api.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VersionResponse(
    @SerialName("version") val version: String
)
