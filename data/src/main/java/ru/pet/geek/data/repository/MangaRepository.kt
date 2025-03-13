package ru.pet.geek.data.repository

import ru.pet.geek.core.LocalResponse
import ru.pet.geek.data.mappers.toAppModel
import ru.pet.geek.data.remote.MangaRemoteSource
import ru.pet.geek.data.remote.RequestsController
import ru.pet.geek.data.toLocalResponse
import ru.pet.geek.domain.entities.dto.MangaRandomCardModel

class MangaRepository(
    private val mangaRemoteSource: MangaRemoteSource,
    private val requestsController: RequestsController
) {
    suspend fun getRandomMangaContent(): LocalResponse<MangaRandomCardModel> = requestsController.prepareRequest {
        mangaRemoteSource.mangaApi.getRandomContent().toLocalResponse {
            toAppModel()
        }
    }
}