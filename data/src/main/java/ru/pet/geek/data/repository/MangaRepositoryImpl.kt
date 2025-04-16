package ru.pet.geek.data.repository

import ru.pet.geek.core.LocalResponse
import ru.pet.geek.data.mappers.toAppModel
import ru.pet.geek.data.remote.MangaRemoteSource
import ru.pet.geek.data.remote.RequestsController
import ru.pet.geek.data.toLocalResponse
import ru.pet.geek.domain.entities.dto.EntryModel
import ru.pet.geek.domain.entities.dto.MangaRandomCardModel

class MangaRepositoryImpl(
    private val mangaRemoteSource: MangaRemoteSource,
    private val requestsController: RequestsController,
) : MangaRepository {
    override suspend fun getRandomMangaContent(): LocalResponse<MangaRandomCardModel> =
        requestsController.prepareRequest {
            mangaRemoteSource.mangaApi.getRandomContent().toLocalResponse {
                data?.toAppModel()
            }
        }

    override suspend fun getRecommendationsMangaContent(id: Int): LocalResponse<List<EntryModel>> =
        requestsController.prepareRequest {
            mangaRemoteSource.mangaApi.getMangaRecommendations(id = id).toLocalResponse {
                data?.mapNotNull { it.entry.toAppModel() }
            }
        }
}
