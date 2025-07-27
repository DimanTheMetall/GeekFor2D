package ru.pet.geek.geekfor2d.di.modules

import dagger.Module
import dagger.Provides
import ru.pet.geek.data.local.FileLocalSource
import ru.pet.geek.data.remote.Client
import ru.pet.geek.data.remote.FileRemoteSource
import ru.pet.geek.data.remote.MangaRemoteSource
import ru.pet.geek.geekfor2d.di.Jikan

@Module
class RemoteSourceModule {

    @Provides
    fun provideMangaRemoteSource(@Jikan client: Client): MangaRemoteSource {
        return MangaRemoteSource(client = client)
    }

    @Provides
    fun provideFileRemoteSource(@Jikan client: Client): FileRemoteSource {
        return FileRemoteSource(client = client)
    }

    @Provides
    fun provideFileLocalSource(): FileLocalSource = FileLocalSource()

}