package me.ilich.kollama

import kotlinx.coroutines.runBlocking
import me.ilich.kollama.me.ilich.kollama.domain.OllamaModelName
import me.ilich.kollama.me.ilich.kollama.ollama

fun main() = runBlocking {
    println("begin")

    val ollama = ollama()

//    println(ollama.version())
//    val models = ollama.models()
//    println(models)
//    models.forEach { model ->
//        ollama.model(model.name)
//    }

    val modelName = OllamaModelName("deepseek-r1:latest")
    println(ollama.model(modelName))

//    val api : OllamaRestApi = OllamaRestApiKtorImpl("http://127.0.0.1:11434")
//
//    val versionResponse = api.version()
//    println(versionResponse)
//
//    val tagsResponse = api.tags()
//    println(tagsResponse)

//    val geenrateRequest = GenerateRequest(
//        model =
//    )
//    val generate = api.generate()
    println("end")
}
