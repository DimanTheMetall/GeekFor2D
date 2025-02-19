package ru.pet.geek.domain.entities.dto

import ru.pet.geek.domain.entities.dto.enums.ContentType
import ru.pet.geek.domain.entities.dto.enums.Status

data class MangaRandomCardModel(
    val malId: Int,
    val url: String? = null,
    val images: ImagesModel,
    val approved: Boolean? = null,
    val titles: List<TitleModel> = emptyList(),
    val title: String,
    val titleEnglish: String? = null,
    val titleJapanese: String? = null,
    val type: ContentType,
    val chapters: Int? = null,
    val volumes: Int? = null,
    val status: Status,
    val publishing: Boolean? = null,
    val score: Int? = null,
    val rank: Int? = null,
    val popularity: Int? = null,
    val synopsis: String? = null,
)