package me.ilich.kollama

import me.ilich.kollama.api.OllamaRestApi
import me.ilich.kollama.list.OllamaModelListResponse

class OllamaClient(
    private val ollamaRestApi: OllamaRestApi
) {

    fun list(): OllamaModelListResponse {
        TODO()
    }

    fun generate(
        model: Model
    ): ChatResponse {
        TODO()
    }

}