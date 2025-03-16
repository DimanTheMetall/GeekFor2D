package ru.pet.geek

import ru.pet.geek.utils.UiInterface

interface VisibilityItem<T> : UiInterface {
    val isVisible: Boolean
    val item: T
}

data class VisibilityItemImpl<T>(
    override val item: T,
    override val isVisible: Boolean = true,
) : VisibilityItem<T>
