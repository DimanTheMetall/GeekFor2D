package ru.pet.geek.core.screens

import ru.pet.geek.core.navigation.Screen

abstract class BaseScreen: Screen {
    override val name: String
        get() = this::javaClass.name
    override val navKey: String
        get() = name.lowercase()
}