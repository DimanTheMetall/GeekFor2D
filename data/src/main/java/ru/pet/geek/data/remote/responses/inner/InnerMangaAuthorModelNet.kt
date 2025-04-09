package ru.pet.geek.data.remote.responses.inner

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class InnerAuthorModelNet(
    @SerialName("mal_id")
    val malId: Int? = null,
    @SerialName("type")
    val type: AuthorTypeNet? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null,
)
