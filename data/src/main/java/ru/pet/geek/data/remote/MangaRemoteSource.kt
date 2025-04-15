package ru.pet.geek.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.pet.geek.data.remote.responses.BaseDataResponse
import ru.pet.geek.data.remote.responses.GetRandomMangaResponse
import ru.pet.geek.data.remote.responses.inner.EntryModelNet

class MangaRemoteSource(
    private val client: GeekClient,
) {
    val mangaApi: MangaRemoteApi by lazy { client.retrofitClient.create(MangaRemoteApi::class.java) }
}

interface MangaRemoteApi {
    @GET("random/manga")
    suspend fun getRandomContent(): Response<BaseDataResponse<GetRandomMangaResponse>>

    @GET("anime/{id}/recommendations")
    suspend fun getMangaRecommendations(
        @Query("id") id: Int,
    ): Response<BaseDataResponse<List<EntryModelNet>>>
}
