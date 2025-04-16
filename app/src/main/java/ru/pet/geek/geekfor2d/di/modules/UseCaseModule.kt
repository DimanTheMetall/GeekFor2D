package ru.pet.geek.geekfor2d.di.modules

import dagger.Module
import dagger.Provides
import ru.pet.geek.data.repository.MangaRepository
import ru.pet.geek.data.usecase.FavoriteIdsListUseCase

@Module
class UseCaseModule {
    @Provides
    fun provideFavoriteIdsListUseCase(mangaRepository: MangaRepository): FavoriteIdsListUseCase = FavoriteIdsListUseCase(mangaRepository)
}
