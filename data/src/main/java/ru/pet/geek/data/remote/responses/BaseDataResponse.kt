package ru.pet.geek.data.remote.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class BaseDataResponse<T>(
    @SerialName("data")
    val data: T? = null
)