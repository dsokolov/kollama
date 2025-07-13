package me.ilich.kollama

import kotlinx.coroutines.runBlocking
import me.ilich.kollama.domain.model.OllamaMessage
import me.ilich.kollama.domain.model.OllamaMessageRole

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
//    if (models.isNotEmpty()) {
//        val firstModel = models.first().name
//        println("Testing generate with model: $firstModel")
//
//        val generateResponse = ollama.generate(
//            model = firstModel,
//            prompt = "Why is the sky blue?",
//            seed = 123,
//        )
//        when (generateResponse) {
//            is OllamaGeneration.Standard -> {
//                println("Generate response: ${generateResponse.response}")
//            }
//        }
//    }

    if (models.isNotEmpty()) {
        val firstModel = models.first().name

        val msg1 = OllamaMessage(
            role = OllamaMessageRole.User,
            content = "Привет! Как жизнь?"
        )
        println(msg1)
        val msg2 = ollama.chat(firstModel, listOf(msg1))
        println(msg2)
    }

    println("end")
}

private fun println(message: OllamaMessage) {
    print(message.role)
    print(": ")
    print(message.content)
    println()
}
