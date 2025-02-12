package ru.pet.geek.data.remote.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.pet.geek.data.remote.responses.inner.ContentTypeNet
import ru.pet.geek.data.remote.responses.inner.InnerImagesResponse
import ru.pet.geek.data.remote.responses.inner.InnerTitleModel

@Serializable
class GetRandomMangaResponse(
    @SerialName("mal_id")
    val malId: Int? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("images")
    val images: InnerImagesResponse? = null,
    @SerialName("approved")
    val approved: Boolean? = null,
    @SerialName("titles")
    val titles: List<InnerTitleModel> = emptyList(),
    @SerialName("title")
    val title: String? = null,
    @SerialName("title_english")
    val titleEnglish: String? = null,
    @SerialName("title_japanese")
    val titleJapanese: String? = null,
    @SerialName("type")
    val type: ContentTypeNet? = null
)

