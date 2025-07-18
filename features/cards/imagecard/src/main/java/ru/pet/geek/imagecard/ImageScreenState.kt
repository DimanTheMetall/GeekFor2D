package ru.pet.geek.imagecard

import ru.pet.geek.utils.UiInterface

interface ImageScreenState : UiInterface {
    data object Loading : ImageScreenState

    data class ImageFromUrl(val imageUrl: String) : ImageScreenState

    data class Error(val e: Throwable) : ImageScreenState
}