package ru.pet.geek.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit

class JikanClient(private val okHttpClient: OkHttpClient): GeekClient {

    override val client = Retrofit.Builder()
        .baseUrl("https://api.jikan.moe/")
        .client(okHttpClient)
        .build()
}