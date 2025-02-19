package ru.pet.geek.domain.entities.dto

data class ImagesModel(
    val jpg: ImageModel = ImageModel(),
    val webp: ImageModel = ImageModel(),
)
