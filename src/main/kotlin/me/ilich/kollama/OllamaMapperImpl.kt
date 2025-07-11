package me.ilich.kollama.me.ilich.kollama

import me.ilich.kollama.api.data.ShowRequest
import me.ilich.kollama.api.data.ShowResponse
import me.ilich.kollama.api.data.TagsResponse
import me.ilich.kollama.api.data.VersionResponse
import me.ilich.kollama.me.ilich.kollama.domain.OllamaModel
import me.ilich.kollama.me.ilich.kollama.domain.OllamaModelFull
import me.ilich.kollama.me.ilich.kollama.domain.OllamaModelName
import me.ilich.kollama.me.ilich.kollama.domain.OllamaVersion

class OllamaMapperImpl : OllamaMapper {

    override fun map(versionResponse: VersionResponse): OllamaVersion =
        versionResponse.version.let(::OllamaVersion)

    override fun map(versionResponse: TagsResponse): List<OllamaModel> =
        versionResponse.models.map { model ->
            OllamaModel(
                name = OllamaModelName(model.name)
            )
        }

    override fun map(name: OllamaModelName): ShowRequest =
        ShowRequest(
            model = name.model
        )

    override fun map(showResponse: ShowResponse): OllamaModelFull =
        OllamaModelFull(
            name = OllamaModelName(showResponse.parameters ?: "")
        )
}
