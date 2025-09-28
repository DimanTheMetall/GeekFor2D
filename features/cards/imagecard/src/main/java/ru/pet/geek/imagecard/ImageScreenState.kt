package ru.pet.geek.imagecard

import android.graphics.Bitmap
import ru.pet.geek.utils.UiInterface
import ru.pet.geek.widgets.circle.CircleButtonInfo

sealed interface ImageScreenState : UiInterface {
    data object Loading : ImageScreenState

    data class Success(val image: Bitmap) : ImageScreenState

    data class Error(val e: Throwable, val refreshButton: CircleButtonInfo) : ImageScreenState
}