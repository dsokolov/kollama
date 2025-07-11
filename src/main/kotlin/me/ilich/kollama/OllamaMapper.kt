package me.ilich.kollama.me.ilich.kollama

import me.ilich.kollama.api.data.ShowRequest
import me.ilich.kollama.api.data.ShowResponse
import me.ilich.kollama.api.data.TagsResponse
import me.ilich.kollama.api.data.VersionResponse
import me.ilich.kollama.me.ilich.kollama.domain.OllamaModel
import me.ilich.kollama.me.ilich.kollama.domain.OllamaModelFull
import me.ilich.kollama.me.ilich.kollama.domain.OllamaModelName
import me.ilich.kollama.me.ilich.kollama.domain.OllamaVersion

interface OllamaMapper {
    fun map(versionResponse: VersionResponse): OllamaVersion
    fun map(versionResponse: TagsResponse): List<OllamaModel>
    fun map(name: OllamaModelName): ShowRequest
    fun map(showResponse: ShowResponse): OllamaModelFull
}
