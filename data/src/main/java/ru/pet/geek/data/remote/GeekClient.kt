package ru.pet.geek.data.remote

import retrofit2.Retrofit

interface GeekClient {
    val retrofitClient: Retrofit
}