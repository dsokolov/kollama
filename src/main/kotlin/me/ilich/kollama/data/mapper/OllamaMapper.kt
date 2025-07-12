package me.ilich.kollama.me.ilich.kollama.data.mapper

import me.ilich.kollama.data.api.data.ShowRequest
import me.ilich.kollama.data.api.data.ShowResponse
import me.ilich.kollama.data.api.data.TagsResponse
import me.ilich.kollama.data.api.data.VersionResponse
import me.ilich.kollama.me.ilich.kollama.domain.model.OllamaModelShort
import me.ilich.kollama.me.ilich.kollama.domain.model.OllamaModelDetails
import me.ilich.kollama.me.ilich.kollama.domain.model.OllamaModelName
import me.ilich.kollama.me.ilich.kollama.domain.model.OllamaVersion

interface OllamaMapper {
    fun map(versionResponse: VersionResponse): OllamaVersion
    fun map(versionResponse: TagsResponse): List<OllamaModelShort>
    fun map(name: OllamaModelName, verbose: Boolean? = null): ShowRequest
    fun map(showResponse: ShowResponse): OllamaModelDetails
}
