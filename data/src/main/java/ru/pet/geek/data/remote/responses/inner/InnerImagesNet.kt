package ru.pet.geek.data.remote.responses.inner

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class InnerImagesNet {
    @SerialName("jpg")
    val jpg: InnerImageNet? = null
    @SerialName("webp")
    val webp: InnerImageNet? = null
}
