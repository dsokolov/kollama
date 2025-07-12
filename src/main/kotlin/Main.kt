package me.ilich.kollama

import kotlinx.coroutines.runBlocking
import me.ilich.kollama.me.ilich.kollama.domain.model.OllamaModelName
import me.ilich.kollama.me.ilich.kollama.ollama

fun main() = runBlocking {
    println("begin")

    val ollama = ollama()

    val version = ollama.version()
    println("Version: $version")
    val models = ollama.models()
    models.forEach { model ->
        println("Model: ${model.name}")
        val details = ollama.model(model.name)
        println("*** begin model file ***\n${details.modelFile}\n*** end model file ***\n")
    }

    println("end")
}
