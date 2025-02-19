package ru.pet.geek.data.remote.responses.inner

import kotlinx.serialization.Serializable
import ru.pet.geek.data.remote.serialisers.ByStringSerializer
import ru.pet.geek.data.remote.serialisers.EnumStringValue

@Serializable(StatusTypeSerializer::class)
enum class StatusNet(override val id: String): EnumStringValue {
    Finished("Finished"),
    Unknown("Unknown"),
}


object StatusTypeSerializer: ByStringSerializer<StatusNet>(
    values = StatusNet.entries.toTypedArray(),
    defaultValue = StatusNet.Unknown
)