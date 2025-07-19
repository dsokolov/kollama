package io.github.dsokolov.kollama.domain

import io.github.dsokolov.kollama.domain.model.OllamaGeneration
import io.github.dsokolov.kollama.domain.model.OllamaMessage
import io.github.dsokolov.kollama.domain.model.OllamaModelName

interface OllamaCompletions {

    /**
     * Generate text using a specific model
     *
     * @param model The name of the model to use
     * @param prompt The text prompt to generate from
     * @param seed Optional seed for reproducible results
     * @return The generated text response
     */
    suspend fun generate(
        model: OllamaModelName,
        prompt: String,
        seed: Int? = null
    ): OllamaGeneration

    /**
     * Send a chat message and get a response
     *
     * @param model The name of the model to use
     * @param messages List of chat messages
     * @return The assistant's response message
     */
    suspend fun chat(
        model: OllamaModelName,
        messages: List<OllamaMessage>,
    ): OllamaMessage

    /**
     * Generate embeddings from a model
     *
     * @param model The name of the model to use
     * @param prompt The text to generate embeddings for
     * @return List of embedding values
     */
    suspend fun embeddings(model: String, prompt: String): List<Double>
}