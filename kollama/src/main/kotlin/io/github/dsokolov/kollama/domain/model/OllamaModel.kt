package io.github.dsokolov.kollama.domain.model


typealias OllamaModelName = String

data class OllamaModelShort(
    val name: OllamaModelName
)

data class OllamaModelDetails(
    val modelFile: String?,
    val parameters: String?,
    val capabilities: List<String>?,
)
