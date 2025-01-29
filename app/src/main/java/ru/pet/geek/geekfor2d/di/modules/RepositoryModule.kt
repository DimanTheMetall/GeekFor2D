package ru.pet.geek.geekfor2d.di.modules

import dagger.Module
import dagger.Provides
import ru.pet.geek.data.remote.MangaRemoteSource
import ru.pet.geek.data.repository.MangaRepository
import ru.pet.geek.geekfor2d.di.AppScope

@Module
class RepositoryModule {

    @AppScope
    @Provides
    fun provideMangaRepository(mangaRemoteSource: MangaRemoteSource): MangaRepository {
        return MangaRepository(mangaRemoteSource = mangaRemoteSource)
    }
}