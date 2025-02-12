package ru.pet.geek.core.navigation

import androidx.compose.runtime.Composable

interface Screen {
    @Composable
    fun Content()
}

interface RootScreen: Screen

