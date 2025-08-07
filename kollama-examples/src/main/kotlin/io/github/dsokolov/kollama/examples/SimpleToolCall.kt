package io.github.dsokolov.kollama.examples

import io.github.dsokolov.kollama.completions
import io.github.dsokolov.kollama.domain.OllamaCompletions
import io.github.dsokolov.kollama.domain.model.OllamaModelName
import io.github.dsokolov.kollama.domain.model.Seed
import io.github.dsokolov.kollama.domain.model.history
import io.github.dsokolov.kollama.ollama
import io.github.dsokolov.kollama.domain.model.ParamType
import io.github.dsokolov.kollama.domain.model.tools
import io.github.dsokolov.kollama.logger.PrintlnLogger
import kotlinx.coroutines.runBlocking

class SimpleToolCallApp(
    private val modelName: OllamaModelName
) {

    private val completions: OllamaCompletions = ollama(logger = PrintlnLogger()).completions(modelName)

    suspend fun start(seed: Seed) {
        println("Tool call example for model `$modelName`")

        val history = history {
            system(
                content = readResourceFile("tool_call_system_prompt.txt")
            )
            user(
                content = "Какая погода в Москве?"
            )
        }
        val tools = tools {
            function(
                name = "get_weather",
                description = "получает текущую погоду для указанного города",
            ) {
                param(
                    name = "city",
                    type = ParamType.STRING,
                    description = "название города",
                    isRequired = true
                )
            }
        }

        val response = completions.chat(
            history = history,
            seed = seed,
            tools = tools,
        )
        println("Response content: ${response.content}")
        println("Response role: ${response.role}")
        println("Response tool calls: ${response.toolCalls}")
        println("Response tool name: ${response.toolName}")
        println("Done!")
    }
}

fun main() = runBlocking {
    val modelName = "qwen2.5-coder:7b"
    val app = SimpleToolCallApp(modelName)

    app.start(420)
}