package ru.pet.geek.data.remote.responses.inner

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class InnerGenreModelNet(
    @SerialName("mal_id")
    val malId: Int? = null,
    @SerialName("type")
    val type: GenreTypeNet? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null,
)
