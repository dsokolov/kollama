package io.github.dsokolov.kollama.examples

import kotlinx.coroutines.runBlocking
import io.github.dsokolov.kollama.domain.model.OllamaMessage
import io.github.dsokolov.kollama.domain.model.OllamaMessageRole
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
        val availableModels = ollamaClient.getManipulations().models()
        println("Available models: ${availableModels.size}")
        
        if (availableModels.isNotEmpty()) {
            val firstModel = availableModels.first().name
            println("Using model: ${firstModel.model}")

            // Create a simple chat message
            val userMessage = OllamaMessage(
                role = OllamaMessageRole.User,
                content = "Привет! Как жизнь?"
            )
            
            println(userMessage)
            
            // Get response from the model
            val assistantResponse = ollamaClient.getCompletions().chat(firstModel, listOf(userMessage))
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
private fun println(message: OllamaMessage) {
    print(message.role)
    print(": ")
    print(message.content)
    println()
} 