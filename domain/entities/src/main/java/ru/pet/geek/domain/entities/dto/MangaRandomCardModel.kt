package ru.pet.geek.domain.entities.dto

import ru.pet.geek.domain.entities.dto.enums.ContentTypeManga
import ru.pet.geek.domain.entities.dto.enums.CurrentContentType
import ru.pet.geek.domain.entities.dto.enums.Status
import ru.pet.geek.domain.entities.interfaces.IdHolder

data class MangaRandomCardModel(
    override val malId: Int,
    val url: String? = null,
    val images: ImagesModel,
    val approved: Boolean? = null,
    val titles: List<TitleModel> = emptyList(),
    val currentType: ContentTypeManga,
    val chapters: Int? = null,
    val volumes: Int? = null,
    val status: Status? = null,
    val publishing: Boolean? = null,
    val score: Float? = null,
    val scoredBy: Int? = null,
    val rank: Int? = null,
    val popularity: Int? = null,
    val synopsis: String? = null,
    val publishedModel: PublishingDateModel? = null,
    val genres: List<GenreModel> = emptyList(),
    val authors: List<AuthorModel> = emptyList(),
) : IdHolder {
    val type: CurrentContentType = CurrentContentType.Manga
}
