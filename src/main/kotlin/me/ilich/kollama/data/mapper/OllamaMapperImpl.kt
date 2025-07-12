package me.ilich.kollama.data.mapper

import me.ilich.kollama.data.api.data.ShowRequest
import me.ilich.kollama.data.api.data.ShowResponse
import me.ilich.kollama.data.api.data.TagsResponse
import me.ilich.kollama.data.api.data.VersionResponse
import me.ilich.kollama.data.api.data.GenerateRequest
import me.ilich.kollama.data.api.data.GenerateResponse
import me.ilich.kollama.domain.model.OllamaModelShort
import me.ilich.kollama.domain.model.OllamaModelDetails
import me.ilich.kollama.domain.model.OllamaModelName
import me.ilich.kollama.domain.model.OllamaVersion
import me.ilich.kollama.domain.model.OllamaGenerated

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

    override fun mapGenerateRequest(
        model: OllamaModelName,
        prompt: String,
        stream: Boolean,
        seed: Int?
    ): GenerateRequest =
        GenerateRequest(
            model = model.model,
            prompt = prompt,
            stream = stream,
            options = if (seed != null) {
                GenerateRequest.Options(
                    seed = seed
                )
            } else {
                null
            }
        )

    override fun mapGenerateResponse(response: GenerateResponse): OllamaGenerated =
        OllamaGenerated.Standard(
            response = response.response
        )
}
