package io.github.dsokolov.kollama.domain

import io.github.dsokolov.kollama.domain.model.OllamaModelName

/**
 * Client interface for interacting with Ollama API
 *
 * This interface provides methods to interact with the Ollama server,
 * including model management, text generation, and chat functionality.
 */
abstract class OllamaClient(
    protected val ollamaManipulations: OllamaManipulations,
) {

    fun getManipulations(): OllamaManipulations = ollamaManipulations

    abstract fun getCompletions(
        model: OllamaModelName,
        isStream: Boolean = false,
    ): OllamaCompletions
}
