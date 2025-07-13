package me.ilich.kollama.domain.model

sealed interface OllamaGeneration {

    data class Standard(
        val response: String
    ) : OllamaGeneration
}
