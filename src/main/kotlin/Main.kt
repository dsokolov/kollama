package me.ilich.kollama

import kotlinx.coroutines.runBlocking
import me.ilich.kollama.domain.model.OllamaModelName
import me.ilich.kollama.domain.model.OllamaGenerated
import me.ilich.kollama.ollama

fun main() = runBlocking {
    println("begin")

    val ollama = ollama()

//    val version = ollama.version()
//    println("Version: $version")
    val models = ollama.models()
//    models.forEach { model ->
//        println("Model: ${model.name}")
//        val details = ollama.model(model.name)
//        println("*** begin model file ***\n${details.modelFile}\n*** end model file ***\n")
//    }

    // Пример использования generate
    if (models.isNotEmpty()) {
        val firstModel = models.first().name
        println("Testing generate with model: $firstModel")
        
        val generateResponse = ollama.generate(
            model = firstModel,
            prompt = "Why is the sky blue?",
            seed = 123,
        )
        when (generateResponse) {
            is OllamaGenerated.Standard -> {
                println("Generate response: ${generateResponse.response}")
            }
        }
    }

    println("end")
}
