package ru.pet.geek.data.remote.responses.inner

import kotlinx.serialization.Serializable
import ru.pet.geek.data.remote.serialisers.ByStringSerializer
import ru.pet.geek.data.remote.serialisers.EnumStringValue

@Serializable(ContentTypeSerializer::class)
enum class ContentTypeNet(override val id: String) : EnumStringValue {
    Manga("Manga"),
    Unknown("Unknown")
}

object ContentTypeSerializer : ByStringSerializer<ContentTypeNet>(
    values = ContentTypeNet.entries.toTypedArray(),
    defaultValue = ContentTypeNet.Unknown
)