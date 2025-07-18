package ru.pet.geek.manga.di

import ru.pet.geek.manga.api.RandomCardMangaDataApi
import ru.pet.geek.manga.api.RandomCardMangaNavApi

interface MangaRandomCardDependencies {
    val mangaRandomCardDataApi: RandomCardMangaDataApi
    val mangaRandomCardNavApi: RandomCardMangaNavApi
}