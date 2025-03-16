package com.example.manga.di

import com.example.manga.api.RandomCardMangaDataApi
import com.example.manga.api.RandomCardMangaNavApi

interface MangaRandomCardDependencies {
    val mangaRandomCardDataApi: RandomCardMangaDataApi
    val mangaRandomCardNavApi: RandomCardMangaNavApi
}