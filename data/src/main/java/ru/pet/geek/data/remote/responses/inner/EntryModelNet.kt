package ru.pet.geek.data.remote.responses.inner

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class EntryModelNet(
    @SerialName("mal_id")
    val malId: Int?,
    @SerialName("images")
    val images: InnerImagesNet?,
    @SerialName("title")
    val title: String?
)
