package ru.pet.geek.data.remote.responses.inner

import kotlinx.serialization.Serializable
import ru.pet.geek.data.remote.serialisers.ByStringSerializer
import ru.pet.geek.data.remote.serialisers.EnumStringValue

@Serializable(ContentTypeMangaSerializer::class)
enum class ContentTypeMangaNet(
    override val id: String,
) : EnumStringValue {
    Manga("Manga"),
    Manhua("Manhua"),
    Manhwa("Manhwa"),
    Novel("Novel"),
    OneShot("OneShot"),
    Doujinshi("Doujinshi"),
    OEL("OEL"),
    LightNovel("LightNovel"),
    Unknown("Unknown"),
}

object ContentTypeMangaSerializer : ByStringSerializer<ContentTypeMangaNet>(
    values = ContentTypeMangaNet.entries.toTypedArray(),
    defaultValue = ContentTypeMangaNet.Unknown,
)
