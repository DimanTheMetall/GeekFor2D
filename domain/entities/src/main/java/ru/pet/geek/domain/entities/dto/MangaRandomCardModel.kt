package ru.pet.geek.domain.entities.dto

import ru.pet.geek.domain.entities.dto.enums.ContentTypeManga
import ru.pet.geek.domain.entities.dto.enums.CurrentContentType
import ru.pet.geek.domain.entities.dto.enums.Status

data class MangaRandomCardModel(
    val malId: String,
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
) {
    val type: CurrentContentType = CurrentContentType.Manga
}
