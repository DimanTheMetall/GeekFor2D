package ru.pet.geek.core.navigation

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import java.io.Serializable
import kotlin.reflect.KClass

interface Screen : Serializable {
    @Composable
    fun Content()
}

fun <T : Screen> KClass<T>.navKey() =
    this.simpleName?.lowercase() ?: error("No simplename available")

interface RootScreen : Screen {
    @get:DrawableRes
    val iconRes: Int
}
