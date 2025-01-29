package ru.pet.geek.geekfor2d.di.modules

import dagger.Module
import dagger.Provides
import ru.pet.geek.data.remote.GeekClient
import ru.pet.geek.data.remote.MangaRemoteSource

@Module
class RemoteSourceModule {

    @Provides
    fun provideMangaRemoteSource(client: GeekClient): MangaRemoteSource {
        return MangaRemoteSource(client)
    }

}