package io.github.dsokolov.kollama.examples

import io.github.dsokolov.kollama.completions
import io.github.dsokolov.kollama.domain.OllamaCompletions
import io.github.dsokolov.kollama.domain.model.History
import io.github.dsokolov.kollama.domain.model.Message
import io.github.dsokolov.kollama.domain.model.MessageRole
import io.github.dsokolov.kollama.domain.model.OllamaModelName
import io.github.dsokolov.kollama.domain.model.Seed
import io.github.dsokolov.kollama.domain.model.assistant
import io.github.dsokolov.kollama.domain.model.history
import io.github.dsokolov.kollama.domain.model.plus
import io.github.dsokolov.kollama.ollama
import kotlinx.coroutines.runBlocking

class Bot(
    history: History,
    private val completions: OllamaCompletions,
    private val initMessage: String?,
    private val onMessage: (String) -> Unit,
) {
    private var history = history

    fun init() {
        if (initMessage != null) {
            history += assistant(initMessage)
            onMessage(initMessage)
        }
    }

    suspend fun phrase(seed: Seed, s: String) {
        val message = Message(
            role = MessageRole.User,
            content = s,
        )
        val replay = completions.chat(history, message, seed)
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
    private val maxIterations: Int = 1000,
) {

    private val completions = ollama().completions(model)

    private val botA = Bot(
        history = history {
            system(readResourceFile("kolobok_system_prompt.txt"))
        },
        initMessage = "Привет! Кто ты?",
        completions = completions
    ) {
        println("КОЛОБОК: $it")
    }

    private val botB = Bot(
        history = history {
            system(readResourceFile("lisa_system_prompt.txt"))
        },
        initMessage = null,
        completions = completions
    ) {
        println("ЛИСА: $it")
    }

    suspend fun start(seed: Seed) {
        botA.init()
        botB.init()

        var requestBot = botA
        var responseBot = botB
        var i = 0
        while (i < maxIterations) {
            val msg = requestBot.getLastMessage()
            responseBot.phrase(seed, msg)
            val temp = requestBot
            requestBot = responseBot
            responseBot = temp
            i++
        }
    }
}

fun main() = runBlocking {
    //val model = "deepseek-r1:1.5b"
    val model = "gurubot/TopicalStorm-uncensored:latest"
    val app = ModelsDialogApp(model)
    app.start(146)
}
