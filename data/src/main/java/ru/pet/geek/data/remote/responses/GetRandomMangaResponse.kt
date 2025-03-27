package ru.pet.geek.data.remote.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.pet.geek.data.remote.responses.inner.ContentTypeNet
import ru.pet.geek.data.remote.responses.inner.InnerImagesResponse
import ru.pet.geek.data.remote.responses.inner.InnerPublishedDateModel
import ru.pet.geek.data.remote.responses.inner.InnerTitleModel
import ru.pet.geek.data.remote.responses.inner.StatusNet

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
    val type: ContentTypeNet? = null,
    @SerialName("chapters")
    val chapters: Int? = null,
    @SerialName("volumes")
    val volumes: Int? = null,
    @SerialName("status")
    val status: StatusNet? = null,
    @SerialName("publishing")
    val publishing: Boolean? = null,
    @SerialName("published")
    val published: InnerPublishedDateModel? = null,
    @SerialName("score")
    val score: Float? = null,
    @SerialName("scored_by")
    val scoredBy: Int? = null,
    @SerialName("rank")
    val rank: Int? = null,
    @SerialName("popularity")
    val popularity: Int? = null,
    @SerialName("synopsis")
    val synopsis: String? = null,
)

