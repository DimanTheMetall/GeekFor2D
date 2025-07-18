package ru.pet.geek.imagecard

import kotlinx.serialization.Serializable

@Serializable
sealed interface ImageCardScreenConfiguration {
    class ImageUrlScreenConfiguration(val imageUrl: String): ImageCardScreenConfiguration
}