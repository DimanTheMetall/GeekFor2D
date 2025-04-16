package ru.pet.geek.data.repository

import ru.pet.geek.core.LocalResponse
import ru.pet.geek.domain.entities.dto.EntryModel
import ru.pet.geek.domain.entities.dto.MangaRandomCardModel

interface MangaRepository {
    suspend fun getRandomMangaContent(): LocalResponse<MangaRandomCardModel>

    suspend fun getRecommendationsMangaContent(id: Int): LocalResponse<List<EntryModel>>
}
