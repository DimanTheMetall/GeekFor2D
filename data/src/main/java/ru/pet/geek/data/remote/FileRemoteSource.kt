package ru.pet.geek.data.remote

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

class FileRemoteSource(
    private val client: Client
) {
    private val remoteSource by lazy { client.retrofitClient.create(FileRemoteSourceApi::class.java) }
}

interface FileRemoteSourceApi {

    @[Streaming GET]
    suspend fun downloadFile(@Url url: Url): Response<ResponseBody>
}