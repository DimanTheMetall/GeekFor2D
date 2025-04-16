package ru.pet.geek.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.pet.geek.data.remote.responses.BaseDataResponse
import ru.pet.geek.data.remote.responses.GetRandomMangaResponse
import ru.pet.geek.data.remote.responses.inner.EntryModelWrapperNet

class MangaRemoteSource(
    private val client: GeekClient,
) {
    val mangaApi: MangaRemoteApi by lazy { client.retrofitClient.create(MangaRemoteApi::class.java) }
}

interface MangaRemoteApi {
    @GET("random/manga")
    suspend fun getRandomContent(): Response<BaseDataResponse<GetRandomMangaResponse>>

    @GET("manga/{id}/recommendations")
    suspend fun getMangaRecommendations(
        @Path("id") id: Int,
    ): Response<BaseDataResponse<List<EntryModelWrapperNet>>>
}
