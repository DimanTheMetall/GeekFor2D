package ru.pet.geek.data.remote

import retrofit2.Retrofit

interface Client {
    val retrofitClient: Retrofit
}