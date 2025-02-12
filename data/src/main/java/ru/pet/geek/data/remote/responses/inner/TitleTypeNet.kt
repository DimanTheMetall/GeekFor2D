package ru.pet.geek.data.remote.responses.inner

import kotlinx.serialization.Serializable
import ru.pet.geek.data.remote.serialisers.ByStringSerializer
import ru.pet.geek.data.remote.serialisers.EnumStringValue

@Serializable(TitleTypeSerializer::class)
enum class TitleTypeNet(override val id: String) : EnumStringValue {
    Default("Default"),
    Synonym("Synonym"),
    Japanese("Japanese"),
    Unknown("Unknown")
}

object TitleTypeSerializer : ByStringSerializer<TitleTypeNet>(
    values = TitleTypeNet.entries.toTypedArray(),
    defaultValue = TitleTypeNet.Unknown
)