package me.ilich.kollama.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Для сериализации значений model_info как строк


@Serializable
/**
 * https://github.com/ollama/ollama/blob/main/docs/api.md#show-model-information
 */
data class ShowResponse(
    @SerialName("modelfile") val modelfile: String,
    @SerialName("parameters") val parameters: String? = null,
//    @SerialName("template") val template: String? = null,
//    @SerialName("details") val details: Details? = null,
//    @SerialName("model_info") val modelInfo: Map<String, @Serializable(with = AnyAsStringSerializer::class) Any?>? = null
)

//@Serializable
//// Детали модели (аналогично TagsResponse.Details, но с parent_model)
//data class Details(
//    @SerialName("parent_model") val parentModel: String? = null,
//    @SerialName("format") val format: String? = null,
//    @SerialName("family") val family: String? = null,
//    @SerialName("families") val families: List<String>? = null,
//    @SerialName("parameter_size") val parameterSize: String? = null,
//    @SerialName("quantization_level") val quantizationLevel: String? = null,
//)
//
//object AnyAsStringSerializer : KSerializer<Any?> {
//    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("AnyAsString", PrimitiveKind.STRING)
//    override fun serialize(encoder: Encoder, value: Any?) {
//        encoder.encodeString(value?.toString() ?: "null")
//    }
//    override fun deserialize(decoder: Decoder): Any? = decoder.decodeString()
//}