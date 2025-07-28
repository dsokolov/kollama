package io.github.dsokolov.kollama.examples

import io.github.dsokolov.kollama.ollama
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val manipulations = ollama().getManipulations()
    manipulations.models().forEach { model ->
        println(model.name)
    }
}
