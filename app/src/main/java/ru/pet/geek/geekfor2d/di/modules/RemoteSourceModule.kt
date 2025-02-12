package ru.pet.geek.geekfor2d.di.modules

import dagger.Module
import dagger.Provides
import ru.pet.geek.data.remote.GeekClient
import ru.pet.geek.data.remote.MangaRemoteSource
import ru.pet.geek.geekfor2d.di.Jikan

@Module
class RemoteSourceModule {

    @Provides
    fun provideMangaRemoteSource(@Jikan client: GeekClient): MangaRemoteSource {
        return MangaRemoteSource(client)
    }

}