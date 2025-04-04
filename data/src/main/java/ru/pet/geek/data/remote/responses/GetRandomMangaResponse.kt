package ru.pet.geek.data.remote.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.pet.geek.data.remote.DEPRECATED_FROM_BACKEND
import ru.pet.geek.data.remote.responses.inner.ContentTypeMangaNet
import ru.pet.geek.data.remote.responses.inner.InnerGenreModelNet
import ru.pet.geek.data.remote.responses.inner.InnerImagesNet
import ru.pet.geek.data.remote.responses.inner.InnerPublishedDateModelNet
import ru.pet.geek.data.remote.responses.inner.InnerTitleModelNet
import ru.pet.geek.data.remote.responses.inner.StatusNet

@Serializable
class GetRandomMangaResponse(
    @SerialName("mal_id")
    val malId: Int? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("images")
    val images: InnerImagesNet? = null,
    @SerialName("approved")
    val approved: Boolean? = null,
    @SerialName("titles")
    val titles: List<InnerTitleModelNet> = emptyList(),
    @SerialName("title")
    @Deprecated(DEPRECATED_FROM_BACKEND)
    val title: String? = null,
    @SerialName("title_english")
    @Deprecated(DEPRECATED_FROM_BACKEND)
    val titleEnglish: String? = null,
    @SerialName("title_japanese")
    @Deprecated(DEPRECATED_FROM_BACKEND)
    val titleJapanese: String? = null,
    @SerialName("type")
    val type: ContentTypeMangaNet? = null,
    @SerialName("chapters")
    val chapters: Int? = null,
    @SerialName("volumes")
    val volumes: Int? = null,
    @SerialName("status")
    val status: StatusNet? = null,
    @SerialName("publishing")
    val publishing: Boolean? = null,
    @SerialName("published")
    val published: InnerPublishedDateModelNet? = null,
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
    @SerialName("genres")
    val genres: List<InnerGenreModelNet> = emptyList()
)
