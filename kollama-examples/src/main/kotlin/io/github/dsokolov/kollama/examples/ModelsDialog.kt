package io.github.dsokolov.kollama.examples

import io.github.dsokolov.kollama.completions
import io.github.dsokolov.kollama.domain.OllamaCompletions
import io.github.dsokolov.kollama.domain.model.Message
import io.github.dsokolov.kollama.domain.model.MessageRole
import io.github.dsokolov.kollama.domain.model.OllamaModelName
import io.github.dsokolov.kollama.domain.model.history
import io.github.dsokolov.kollama.domain.model.plus
import io.github.dsokolov.kollama.ollama
import kotlinx.coroutines.runBlocking

class Bot(
    private val systemPrompt: String,
    private val completions: OllamaCompletions,
    private val onMessage: (String) -> Unit,
) {
    private var history = history {
        system(systemPrompt)
    }

    suspend fun phrase(s: String) {
        val message = Message(
            role = MessageRole.User,
            content = s,
        )
        val replay = completions.chat(history, message)
        history += message
        history += replay
        onMessage(replay.content)
    }

    fun getLastMessage(): String = history.messages
        .lastOrNull { it.role is MessageRole.Assistant }
        ?.content
        ?: ""
}


class ModelsDialogApp(
    model: OllamaModelName,
    private val maxIterations: Int = 10,
) {

    private val completions = ollama().completions(model)

    private val botA = Bot(
        systemPrompt = readResourceFile("kolobok_system_prompt.txt"),
        completions = completions
    ) {
        println("A: $it")
    }
    private val botB = Bot(
        systemPrompt = readResourceFile("lisa_system_prompt.txt"),
        completions = completions
    ) {
        println("B: $it")
    }

    suspend fun start() {
        var requestBot = botB
        var responseBot = botA
        var i = 0
        while (i < maxIterations) {
            println("*** $i ***")
            val msg = requestBot.getLastMessage()
            responseBot.phrase(msg)
            val temp = requestBot
            requestBot = responseBot
            responseBot = temp
            i++
        }
    }
}

fun main() = runBlocking {
    val model = "deepseek-r1:1.5b"
    //val model = "gurubot/TopicalStorm-uncensored:latest"
    val app = ModelsDialogApp(model)
    app.start()
}
