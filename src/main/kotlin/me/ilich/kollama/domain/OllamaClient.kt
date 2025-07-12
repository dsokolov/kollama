package me.ilich.kollama.domain

import me.ilich.kollama.domain.model.OllamaGenerated
import me.ilich.kollama.domain.model.OllamaModelDetails
import me.ilich.kollama.domain.model.OllamaModelName
import me.ilich.kollama.domain.model.OllamaModelShort
import me.ilich.kollama.domain.model.OllamaVersion

interface OllamaClient {
    suspend fun version(): OllamaVersion
    suspend fun models(): List<OllamaModelShort>
    suspend fun model(modelName: OllamaModelName, verbose: Boolean? = null): OllamaModelDetails
    suspend fun generate(
        model: OllamaModelName,
        prompt: String,
        seed: Int? = null
    ): OllamaGenerated
}
