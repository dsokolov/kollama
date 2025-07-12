package me.ilich.kollama.me.ilich.kollama.data.mapper

import me.ilich.kollama.data.api.data.ShowRequest
import me.ilich.kollama.data.api.data.ShowResponse
import me.ilich.kollama.data.api.data.TagsResponse
import me.ilich.kollama.data.api.data.VersionResponse
import me.ilich.kollama.me.ilich.kollama.domain.model.OllamaModelShort
import me.ilich.kollama.me.ilich.kollama.domain.model.OllamaModelDetails
import me.ilich.kollama.me.ilich.kollama.domain.model.OllamaModelName
import me.ilich.kollama.me.ilich.kollama.domain.model.OllamaVersion

internal class OllamaMapperImpl : OllamaMapper {

    override fun map(versionResponse: VersionResponse): OllamaVersion =
        versionResponse.version.let(::OllamaVersion)

    override fun map(versionResponse: TagsResponse): List<OllamaModelShort> =
        versionResponse.models.map { model ->
            OllamaModelShort(
                name = OllamaModelName(model.name),
            )
        }

    override fun map(name: OllamaModelName, verbose: Boolean?): ShowRequest =
        ShowRequest(
            model = name.model,
            verbose = verbose,
        )

    override fun map(showResponse: ShowResponse): OllamaModelDetails =
        OllamaModelDetails(
            modelFile = showResponse.modelfile,
            parameters = showResponse.parameters,
        )
}
