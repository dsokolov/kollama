package io.github.dsokolov.kollama.examples

import io.github.dsokolov.kollama.manipulations
import io.github.dsokolov.kollama.ollama
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val manipulations = ollama().manipulations()
    manipulations.models().forEach { model ->
        println(model.name)
    }
}
