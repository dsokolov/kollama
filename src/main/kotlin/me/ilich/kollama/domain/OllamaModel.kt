package me.ilich.kollama.me.ilich.kollama.domain

data class OllamaModel(
    val name: OllamaModelName
)

data class OllamaModelFull(
    val name: OllamaModelName
)

@JvmInline
value class OllamaModelName(val model: String)
