package io.github.dsokolov.kollama.examples

import io.github.dsokolov.kollama.domain.OllamaCompletions
import io.github.dsokolov.kollama.domain.model.History
import io.github.dsokolov.kollama.domain.model.Message
import io.github.dsokolov.kollama.domain.model.MessageRole
import io.github.dsokolov.kollama.domain.model.OllamaModelName
import io.github.dsokolov.kollama.domain.model.Seed
import io.github.dsokolov.kollama.domain.model.history
import io.github.dsokolov.kollama.domain.model.plus
import io.github.dsokolov.kollama.ollama
import kotlinx.coroutines.runBlocking

class SimpleChatApp(
    private val model: OllamaModelName,
    private val callback: ChatCallback,
) {
    private val completions: OllamaCompletions = ollama().getCompletions(model)
    private var history: History = history()

    suspend fun start(seed: Seed? = null) {
        println("Чат с моделью ${model.model} начат. Введите 'exit' для выхода.")
        history = history {
            system(readResourceFile("friendly_chatbot_system_prompt.txt"))
        }
        while (true) {
            val userMessage = callback.onEnterUserMessage()

            if (userMessage.lowercase() == "exit") {
                println("До свидания!")
                break
            }

            if (userMessage.isNotBlank()) {
                callback.onShowUserMessage(userMessage)

                val chatMessage = Message(
                    role = MessageRole.User,
                    content = userMessage
                )
                val newMessage = completions.chat(
                    history = history,
                    message = chatMessage,
                    seed = seed
                )
                callback.onShowRobotMessage(newMessage.content)
                history += newMessage
            }
        }
    }
}

interface ChatCallback {
    fun onShowUserMessage(s: String)
    fun onShowRobotMessage(s: String)
    fun onEnterUserMessage(): String
}

fun main() = runBlocking {
    //val model = OllamaModelName("deepseek-r1:1.5b")
    val model = OllamaModelName("gurubot/TopicalStorm-uncensored:latest")
    val callback: ChatCallback = object : ChatCallback {
        override fun onShowUserMessage(s: String) {
            println("[USER]: $s")
        }

        override fun onShowRobotMessage(s: String) {
            println("[ROBOT]: $s")
        }

        override fun onEnterUserMessage(): String {
            return readLine() ?: ""
        }

    }
    val app = SimpleChatApp(model, callback)
    app.start(1000)
}