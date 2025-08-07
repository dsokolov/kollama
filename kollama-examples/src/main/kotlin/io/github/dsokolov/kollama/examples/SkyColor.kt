package io.github.dsokolov.kollama.examples

import io.github.dsokolov.kollama.completions
import io.github.dsokolov.kollama.manipulations
import io.github.dsokolov.kollama.ollama
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val ollama = ollama()
    val models = ollama.manipulations().models()
    if (models.isEmpty()) {
        print("No models installed")
    } else {
        val model = models.first()
        val generation = ollama.completions(model.name).generate("Why is the sky blue?")
        println(generation.response)
    }
}
