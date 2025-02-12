package ru.pet.geek.data.repository

import ru.pet.geek.core.LocalResponse
import ru.pet.geek.data.remote.MangaRemoteSource
import ru.pet.geek.data.remote.RequestsController
import ru.pet.geek.data.toLocalResponse
import ru.pet.geek.domain.entities.dto.TestAzaza

class MangaRepository(
    val mangaRemoteSource: MangaRemoteSource,
    private val requestsController: RequestsController
) {
    suspend fun getRandomMangaContent(): LocalResponse<TestAzaza> = requestsController.prepareRequest {
        mangaRemoteSource.mangaApi.getRandomContent().toLocalResponse {
            TestAzaza("1")
        }
    }

}