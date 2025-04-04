package ru.pet.geek.data.remote.responses.inner

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class InnerPublishedDateModelNet(
    @SerialName("from")
    val from: String? = null,
    @SerialName("to")
    val to: String? = null,
)
