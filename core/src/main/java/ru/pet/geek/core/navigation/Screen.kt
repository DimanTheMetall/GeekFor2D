package ru.pet.geek.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable

@Stable
interface Screen {
    @Composable
    fun Content()
}

interface RootScreen : Screen
