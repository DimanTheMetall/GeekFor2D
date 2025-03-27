package ru.pet.geek.data.remote.serialisers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

open class ByStringSerializer<T>(
    private val values: Array<T>,
    private val defaultValue: T,
) : KSerializer<T?> where T : Enum<T>, T : EnumStringValue {
    override val descriptor: SerialDescriptor
        get() =
            PrimitiveSerialDescriptor(
                "ru.pet.geek.data.remote.serialisers.ByStringSerializer",
                PrimitiveKind.STRING,
            )

    override fun serialize(
        encoder: Encoder,
        value: T?,
    ) {
        value?.id?.let { encoder.encodeString(it) }
    }

    override fun deserialize(decoder: Decoder): T {
        val id = decoder.decodeString()
        val enum = values.firstOrNull { it.id.equals(id, true) }
        return enum ?: defaultValue
    }
}

open class ByStringNullableSerializer<T>(
    private val values: Array<T>,
) : KSerializer<T?> where T : Enum<T>, T : EnumStringValue {
    override val descriptor: SerialDescriptor
        get() =
            PrimitiveSerialDescriptor(
                "ru.pet.geek.data.remote.serialisers.ByStringSerializer",
                PrimitiveKind.STRING,
            )

    override fun serialize(
        encoder: Encoder,
        value: T?,
    ) {
        value?.id?.let { encoder.encodeString(it) }
    }

    override fun deserialize(decoder: Decoder): T? {
        val id = decoder.decodeString()
        val enum = values.firstOrNull { it.id.equals(id, true) }
        return enum
    }
}

interface EnumStringValue {
    val id: String
}
