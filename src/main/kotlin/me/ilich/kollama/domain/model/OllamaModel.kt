package me.ilich.kollama.me.ilich.kollama.domain.model


@JvmInline
value class OllamaModelName(val model: String)

data class OllamaModelShort(
    val name: OllamaModelName
)

data class OllamaModelDetails(
    val modelFile: String?,
    val parameters: String?,
)
