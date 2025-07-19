package io.github.dsokolov.kollama.domain

import io.github.dsokolov.kollama.domain.model.OllamaGeneration
import io.github.dsokolov.kollama.domain.model.OllamaMessage
import io.github.dsokolov.kollama.domain.model.OllamaModelDetails
import io.github.dsokolov.kollama.domain.model.OllamaModelName
import io.github.dsokolov.kollama.domain.model.OllamaModelShort
import io.github.dsokolov.kollama.domain.model.OllamaVersion
import io.github.dsokolov.kollama.domain.OllamaCompletions
import io.github.dsokolov.kollama.domain.OllamaManipulations

/**
 * Client interface for interacting with Ollama API
 *
 * This interface provides methods to interact with the Ollama server,
 * including model management, text generation, and chat functionality.
 */
abstract class OllamaClient(
    protected val ollamaManipulations: OllamaManipulations,
    protected val ollamaCompletions: OllamaCompletions,
) {

    fun getManipulations(): OllamaManipulations = ollamaManipulations

    fun getCompletions(): OllamaCompletions = ollamaCompletions
}
