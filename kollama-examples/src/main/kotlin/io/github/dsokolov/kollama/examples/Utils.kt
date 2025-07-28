package io.github.dsokolov.kollama.examples

fun readResourceFile(fileName: String): String {
    val inputStream = String.javaClass.classLoader.getResourceAsStream(fileName)
    return inputStream?.bufferedReader()?.use { it.readText() }
        ?: throw IllegalArgumentException("Resource file '$fileName' not found")
}
