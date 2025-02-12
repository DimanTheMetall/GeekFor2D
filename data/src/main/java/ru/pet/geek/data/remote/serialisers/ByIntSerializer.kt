package ru.pet.geek.data.remote.serialisers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


open class ByIntSerializer<T>(
    private val values: Array<T>,
    private val default: T
) :
    KSerializer<T?> where T : Enum<T>, T : EnumIntValue {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor(
            "ru.pet.geek.data.remote.serialisers.ByIntSerializer",
            PrimitiveKind.STRING
        )

    override fun serialize(encoder: Encoder, value: T?) {
        value?.id?.let { encoder.encodeInt(it) }
    }

    override fun deserialize(decoder: Decoder): T? {
        val id = decoder.decodeInt()
        return values.firstOrNull { it.id == id } ?: default
    }
}

interface EnumIntValue {
    val id: Int
}