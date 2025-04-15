package com.example.manga.api

import ru.pet.geek.core.LocalResponse
import ru.pet.geek.domain.entities.dto.EntryModel
import ru.pet.geek.domain.entities.dto.MangaRandomCardModel

interface RandomCardMangaDataApi {
    suspend fun getRandomCard(): LocalResponse<MangaRandomCardModel>

    suspend fun getMangaRecommendations(mangaId: Int): LocalResponse<List<EntryModel>>
}
