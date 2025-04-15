package ru.pet.geek.data.remote

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class JikanClient(
    okHttpClient: OkHttpClient,
) : GeekClient {
    private val json =
        Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
            explicitNulls = false
        }

    override val retrofitClient =
        Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl("https://api.jikan.moe/v4/")
            .addConverterFactory(json.asConverterFactory("application/json; charset=UTF8".toMediaType()))
            .build()
}
