package ru.pet.geek.navigationcontroller

import ru.pet.geek.core.navigation.RootScreen
import ru.pet.geek.core.navigation.Screen

sealed interface NavigationAction {
    class OpenNext(val screen: Screen): NavigationAction
    data object Back: NavigationAction
    class SelectStack(val root: RootScreen): NavigationAction
}