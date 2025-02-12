package ru.pet.geek.api

import androidx.annotation.DrawableRes
import ru.pet.geek.utils.UiInterface

interface IcResHolderUi: UiInterface {
    @get:DrawableRes
    val icRes: Int
}