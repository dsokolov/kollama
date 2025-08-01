package io.github.dsokolov.kollama.examples

import io.github.dsokolov.kollama.completions
import io.github.dsokolov.kollama.domain.model.OllamaModelName
import io.github.dsokolov.kollama.domain.model.Seed
import io.github.dsokolov.kollama.domain.model.history
import io.github.dsokolov.kollama.ollama
import kotlinx.coroutines.runBlocking

class TextComparatorApp(
    model: OllamaModelName
) {

    private companion object {
        const val TEXT_1_BEGIN_TAG = "<TEXT1>"
        const val TEXT_1_END_TAG = "</TEXT1>"
        const val TEXT_2_BEGIN_TAG = "<TEXT2>"
        const val TEXT_2_END_TAG = "</TEXT2>"
        const val SCORE_BEGIN_TAG = "<SCORE>"
        const val SCORE_END_TAG = "</SCORE>"
    }

    private val ollama = ollama()
    private val completions = ollama.completions(model)

    suspend fun run(
        text1: String,
        text2: String,
        seed: Seed? = null,
    ): String? {
        val systemPrompt = readResourceFile("text_comparator_system_prompt.txt")
        val userPrompt = "$TEXT_1_BEGIN_TAG$text1$TEXT_1_END_TAG" +
                "$TEXT_2_BEGIN_TAG$text2$TEXT_2_END_TAG"
        val history = history {
            system(systemPrompt)
            user(userPrompt)
        }
        val assistantResponse = completions.chat(
            history = history,
            seed = seed,
        )
        val startIndex = assistantResponse.content.indexOf(SCORE_BEGIN_TAG)
        val endIndex = assistantResponse.content.indexOf(SCORE_END_TAG, startIndex)
        if (startIndex >= 0 && endIndex >= 0) {
            val result = assistantResponse.content.substring(startIndex + SCORE_BEGIN_TAG.length, endIndex)
            return result
        } else {
            return null
        }
    }
}

fun main() = runBlocking {
    //val model = OllamaModelName("gurubot/TopicalStorm-uncensored:latest")
    val model = "deepseek-r1:1.5b"
    val app = TextComparatorApp(model)
    val text1 = "Жара"
    val text2 = "boat"
    val score = app.run(
        text1 = text1,
        text2 = text2,
        seed = 123,
    )
    println(score)
}
