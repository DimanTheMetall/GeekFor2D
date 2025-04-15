package ru.pet.geek.utils

@JvmInline
value class ListUiDecorator<T>(
    val items: List<T>,
) : UiInterface
