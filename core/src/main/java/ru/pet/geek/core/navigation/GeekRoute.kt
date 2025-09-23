package ru.pet.geek.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface GeekRoute {

    @Serializable
    sealed interface ContainerRoute : GeekRoute {
        @Serializable
        data object FeedContainer : ContainerRoute

        @Serializable
        data object FavoriteContainer : ContainerRoute
    }

    @Serializable
    data object FeedRoute : GeekRoute

    @Serializable
    data object MangaRandomCardRoute : GeekRoute
    @Serializable
    data object FavoriteRoute : GeekRoute

    @Serializable
    data class ImageRoute(
        val imageUrl: String,
        val contentId: Int,
    ) : GeekRoute
}