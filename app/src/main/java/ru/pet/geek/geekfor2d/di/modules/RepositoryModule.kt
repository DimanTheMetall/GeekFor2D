package ru.pet.geek.geekfor2d.di.modules

import dagger.Module
import dagger.Provides
import ru.pet.geek.data.remote.CounterRequestController
import ru.pet.geek.data.remote.MangaRemoteSource
import ru.pet.geek.data.remote.RequestsController
import ru.pet.geek.data.repository.MangaRepository
import ru.pet.geek.data.repository.MangaRepositoryImpl
import ru.pet.geek.geekfor2d.di.AppScope

@Module
class RepositoryModule {
    @[AppScope Provides]
    fun provideLimitRequestController(): RequestsController = CounterRequestController()

    @[AppScope Provides]
    fun provideMangaRepository(
        mangaRemoteSource: MangaRemoteSource,
        requestsController: RequestsController,
    ): MangaRepository = MangaRepositoryImpl(mangaRemoteSource = mangaRemoteSource, requestsController = requestsController)
}
