package io.github.dsokolov.kollama.domain

import io.github.dsokolov.kollama.domain.model.History
import io.github.dsokolov.kollama.domain.model.OllamaGeneration
import io.github.dsokolov.kollama.domain.model.Message
import io.github.dsokolov.kollama.domain.model.OllamaModelName
import io.github.dsokolov.kollama.domain.model.Seed

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
     * @param messages List of chat messages
     * @return The assistant's response message
     */
    suspend fun chat(
        history: History,
        seed: Seed? = null,
    ): Message

    /**
     * Generate embeddings from a model
     *
     * @param prompt The text to generate embeddings for
     * @return List of embedding values
     */
    suspend fun embeddings(prompt: String): List<Double>
}
