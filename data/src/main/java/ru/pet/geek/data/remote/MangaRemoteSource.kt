package ru.pet.geek.data.remote

import retrofit2.Response
import retrofit2.http.GET
import ru.pet.geek.data.remote.responses.GetRandomMangaResponse

class MangaRemoteSource(private val client: GeekClient) {
    val mangaApi: MangaRemoteApi by lazy { client.retrofitClient.create(MangaRemoteApi::class.java) }
}

interface MangaRemoteApi {

    @GET("random/manga")
    suspend fun getRandomContent(): Response<GetRandomMangaResponse>
}