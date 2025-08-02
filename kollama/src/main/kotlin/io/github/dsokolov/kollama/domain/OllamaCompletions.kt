package io.github.dsokolov.kollama.domain

import io.github.dsokolov.kollama.domain.model.History
import io.github.dsokolov.kollama.domain.model.OllamaGeneration
import io.github.dsokolov.kollama.domain.model.Message
import io.github.dsokolov.kollama.domain.model.OllamaModelName
import io.github.dsokolov.kollama.domain.model.Seed
import io.github.dsokolov.kollama.domain.model.Tool
import io.github.dsokolov.kollama.domain.model.emptyHistory

interface OllamaCompletions {

    /**
     * Generate text using a specific model
     *
     * @param prompt The text prompt to generate from
     * @param seed Optional seed for reproducible results
     * @return The generated text response
     */
    suspend fun generate(
        prompt: String,
        seed: Int? = null
    ): OllamaGeneration

    /**
     * Send a chat message and get a response
     *
     * @param history Chat history
     * @param message Optional additional message
     * @param seed Optional seed for reproducible results
     * @param tools Optional list of tools available to the model
     * @return The assistant's response message
     */
    suspend fun chat(
        history: History = emptyHistory(),
        message: Message? = null,
        seed: Seed? = null,
        tools: List<Tool>? = null,
    ): Message

    /**
     * Generate embeddings from a model
     *
     * @param prompt The text to generate embeddings for
     * @return List of embedding values
     */
    suspend fun embeddings(prompt: String): List<Double>
}
