package ru.pet.geek.navigationcontroller

import ru.pet.geek.core.navigation.GeekRoute

sealed interface NavigationAction {
    class OpenNext(val route: GeekRoute): NavigationAction
    data object Back: NavigationAction
    class SelectStack(val route: GeekRoute.ContainerRoute): NavigationAction
}