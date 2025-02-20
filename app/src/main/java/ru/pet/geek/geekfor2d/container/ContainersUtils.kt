package ru.pet.geek.geekfor2d.container

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import ru.pet.geek.favorite.FavoriteScreen
import ru.pet.geek.features.feed.FeedScreen


internal fun NavGraphBuilder.addRootsScreen() {
    navigation<FeedContainer>(startDestination = FeedScreen::class) {
        addSimplesScreens()
    }

    navigation<FavoriteContainer>(startDestination = FavoriteScreen::class) {
        addSimplesScreens()
    }
}

internal fun NavGraphBuilder.addSimplesScreens() {
    composable<FeedScreen> {
        val screen = it.toRoute<FeedScreen>()
        screen.Content()
    }

    composable<FavoriteScreen> {
        val screen = it.toRoute<FavoriteScreen>()
        screen.Content()
    }
}