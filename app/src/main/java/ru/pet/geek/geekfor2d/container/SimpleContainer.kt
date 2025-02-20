package ru.pet.geek.geekfor2d.container

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable
import ru.pet.geek.core.screens.BaseRootScreen

@Serializable
internal abstract class SimpleContainer : BaseRootScreen()

@Serializable
internal class FeedContainer : SimpleContainer() {
    @Composable
    override fun Content() = Unit
}
//
@Serializable
internal class FavoriteContainer : SimpleContainer() {
    @Composable
    override fun Content() = Unit
}