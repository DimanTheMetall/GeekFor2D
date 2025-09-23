package ru.pet.geek.imagecard.di

import ru.pet.geek.imagecard.api.ImageCardDataApi
import ru.pet.geek.imagecard.api.ImageCardNavApi

interface ImageCardDependencies {
    val imageCardNavApi: ImageCardNavApi
    val imageCardDataApi: ImageCardDataApi
}