package ru.pet.geek.data.remote.responses.inner

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class EntryModelWrapperNet(
    @SerialName("entry")
    val entry: EntryModelNet,
) 
