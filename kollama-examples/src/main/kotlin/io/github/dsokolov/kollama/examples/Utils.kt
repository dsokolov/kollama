package io.github.dsokolov.kollama.examples

fun readResourceFile(fileName: String): String {
    val inputStream = String.javaClass.classLoader.getResourceAsStream(fileName)
    return inputStream?.bufferedReader()?.use { it.readText() }
        ?: throw IllegalArgumentException("Resource file '$fileName' not found")
}

fun String.tagSubstring(openTag: String, closeTag: String): String? {
    var result: String? = null
    val openIndex = indexOf(openTag)
    if (openIndex != -1) {
        val closeIndex = indexOf(closeTag, openIndex)
        if (closeIndex != -1) {
            result = substring(openIndex + openTag.length, closeIndex)
        }
    }
    return result
}
