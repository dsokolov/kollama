package me.ilich.kollama.domain.model

sealed interface OllamaGenerated {

    data class Standard(
        val response: String
    ) : OllamaGenerated
}
