package ru.pet.geek.geekfor2d.container

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import ru.pet.geek.manga.MangaRandomCardScreen
import ru.pet.geek.favorite.FavoriteScreen
import ru.pet.geek.features.feed.FeedScreen
import ru.pet.geek.core.navigation.GeekRoute
import ru.pet.geek.imagecard.ImageCardScreen

internal fun NavGraphBuilder.createGraph() {
    navigation<GeekRoute.ContainerRoute.FeedContainer>(startDestination = GeekRoute.FeedRoute::class) {
        addSimplesScreens()
    }

    navigation<GeekRoute.ContainerRoute.FavoriteContainer>(startDestination = GeekRoute.FavoriteRoute::class) {
        addSimplesScreens()
    }
}

private fun NavGraphBuilder.addSimplesScreens() {
    composable<GeekRoute.FeedRoute> {
        FeedScreen().Content()
    }

    composable<GeekRoute.FavoriteRoute> {
        FavoriteScreen().Content()
    }

    composable<GeekRoute.MangaRandomCardRoute> {
        MangaRandomCardScreen().Content()
    }

    composable<GeekRoute.ImageRoute> {
        val route = it.toRoute<GeekRoute.ImageRoute>()
        ImageCardScreen(screenConfiguration = route)
    }
}
