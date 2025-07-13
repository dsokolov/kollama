package me.ilich.kollama.domain

import me.ilich.kollama.domain.model.OllamaGeneration
import me.ilich.kollama.domain.model.OllamaMessage
import me.ilich.kollama.domain.model.OllamaModelDetails
import me.ilich.kollama.domain.model.OllamaModelName
import me.ilich.kollama.domain.model.OllamaModelShort
import me.ilich.kollama.domain.model.OllamaVersion
import me.ilich.kollama.me.ilich.kollama.domain.OllamaCompletions
import me.ilich.kollama.me.ilich.kollama.domain.OllamaManipulations

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
