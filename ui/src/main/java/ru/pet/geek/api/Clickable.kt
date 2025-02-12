package ru.pet.geek.api

import ru.pet.geek.utils.UiInterface

interface Clickable: UiInterface {
    val onClick: () -> Unit
}