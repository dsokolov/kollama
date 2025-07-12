package me.ilich.kollama.me.ilich.kollama.domain

import me.ilich.kollama.me.ilich.kollama.domain.model.OllamaModelDetails
import me.ilich.kollama.me.ilich.kollama.domain.model.OllamaModelName
import me.ilich.kollama.me.ilich.kollama.domain.model.OllamaModelShort
import me.ilich.kollama.me.ilich.kollama.domain.model.OllamaVersion

interface OllamaClient {
    suspend fun version(): OllamaVersion
    suspend fun models(): List<OllamaModelShort>
    suspend fun model(modelName: OllamaModelName, verbose: Boolean? = null): OllamaModelDetails
}
