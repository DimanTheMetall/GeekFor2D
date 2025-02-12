package ru.pet.geek.data.remote.responses.inner

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class InnerImagesResponse {
    @SerialName("jpg")
    val jpg: InnerImageResponse? = null
    @SerialName("webp")
    val webp: InnerImageResponse? = null
}