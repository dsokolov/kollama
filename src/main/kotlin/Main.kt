package me.ilich.kollama

import kotlinx.coroutines.runBlocking
import me.ilich.kollama.api.OllamaRestApi
import me.ilich.kollama.api.OllamaRestApiKtorImpl
import me.ilich.kollama.api.data.GenerateRequest

fun main() = runBlocking {
    println("begin")
    val api : OllamaRestApi = OllamaRestApiKtorImpl("http://127.0.0.1:11434")

    val versionResponse = api.version()
    println(versionResponse)

    val tagsResponse = api.tags()
    println(tagsResponse)

//    val geenrateRequest = GenerateRequest(
//        model =
//    )
//    val generate = api.generate()
    println("end")
}
