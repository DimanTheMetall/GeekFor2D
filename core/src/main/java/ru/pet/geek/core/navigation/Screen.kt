package ru.pet.geek.core.navigation

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import java.io.Serializable

interface Screen : Serializable {
    val name: String
    val navKey: String

    @Composable
    fun Content()
}

interface ContainerScreen : Screen

interface RootScreen : Screen {
    @get:DrawableRes
    val iconRes: Int
}
