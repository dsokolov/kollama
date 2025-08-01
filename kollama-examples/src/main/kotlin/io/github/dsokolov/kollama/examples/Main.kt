package io.github.dsokolov.kollama.examples

import io.github.dsokolov.kollama.completions
import kotlinx.coroutines.runBlocking
import io.github.dsokolov.kollama.domain.model.Message
import io.github.dsokolov.kollama.domain.model.MessageRole
import io.github.dsokolov.kollama.domain.model.history
import io.github.dsokolov.kollama.manipulations
import io.github.dsokolov.kollama.ollama

/**
 * Main entry point for the Kollama application
 *
 * This function demonstrates basic usage of the Ollama client
 * by listing available models and performing a simple chat interaction.
 */
fun main() = runBlocking {
    println("Starting Kollama client...")

    val ollamaClient = ollama()

    try {
        // Get available models
        val availableModels = ollamaClient.manipulations().models()
        println("Available models: ${availableModels.size}")

        if (availableModels.isNotEmpty()) {
            val firstModel = availableModels.first().name
            println("Using model: $firstModel")

            val messages = history {
                system("Ты говоришь только по-русски.")
                user("Привет! Как жизнь?")
            }

            // Get response from the model
            val assistantResponse = ollamaClient.completions(firstModel).chat(messages)
            println(assistantResponse)
        } else {
            println("No models available")
        }
    } catch (e: Exception) {
        println("Error: ${e.message}")
        e.printStackTrace()
    }

    println("Kollama client finished")
}

/**
 * Helper function to print OllamaMessage in a readable format
 */
private fun println(message: Message) {
    print(message.role)
    print(": ")
    print(message.content)
    println()
} 