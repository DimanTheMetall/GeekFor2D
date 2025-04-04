package ru.pet.geek.data.remote.responses.inner

import kotlinx.serialization.Serializable
import ru.pet.geek.data.remote.serialisers.ByStringNullableSerializer
import ru.pet.geek.data.remote.serialisers.EnumStringValue

@Serializable(GenreTypeSerializer::class)
enum class GenreTypeNet(
    override val id: String,
) : EnumStringValue {
    Manga("manga"),
    Anime("anime"),
}

object GenreTypeSerializer : ByStringNullableSerializer<GenreTypeNet>(values = GenreTypeNet.entries.toTypedArray())
