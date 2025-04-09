package ru.pet.geek.data.remote.responses.inner

import kotlinx.serialization.Serializable
import ru.pet.geek.data.remote.serialisers.ByStringNullableSerializer
import ru.pet.geek.data.remote.serialisers.EnumStringValue

@Serializable(AuthorTypeSerializer::class)
enum class AuthorTypeNet(
    override val id: String,
) : EnumStringValue {
    Manga("manga"),
}

object AuthorTypeSerializer : ByStringNullableSerializer<AuthorTypeNet>(values = AuthorTypeNet.entries.toTypedArray())
