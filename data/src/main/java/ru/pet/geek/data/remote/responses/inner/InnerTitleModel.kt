package ru.pet.geek.data.remote.responses.inner

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class InnerTitleModel(
    @SerialName("type")
    val type: TitleTypeNet? = null,
    @SerialName("title")
    val title: String? = null
)