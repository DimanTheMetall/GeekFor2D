package ru.pet.geek.data.remote.responses.inner

import kotlinx.serialization.Serializable
import ru.pet.geek.data.remote.serialisers.ByStringNullableSerializer
import ru.pet.geek.data.remote.serialisers.EnumStringValue

@Serializable(StatusTypeSerializer::class)
enum class StatusNet(
    override val id: String,
) : EnumStringValue {
    Finished("Finished"),
    Publishing("Publishing"),
    OnHiatus("On Hiatus"),
    Discontinued("Discontinued"),
    NotYetPublished("Not yet published"),
}

object StatusTypeSerializer : ByStringNullableSerializer<StatusNet>(
    values = StatusNet.entries.toTypedArray(),
)
