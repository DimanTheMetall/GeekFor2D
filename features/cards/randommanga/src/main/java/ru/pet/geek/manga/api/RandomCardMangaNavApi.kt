package ru.pet.geek.manga.api

interface RandomCardMangaNavApi {
    fun goBack()

    fun navigateToImageRemoteScreen(contentId: Int, imageUrl: String)
}