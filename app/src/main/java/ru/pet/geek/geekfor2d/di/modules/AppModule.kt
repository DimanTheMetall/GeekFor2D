package ru.pet.geek.geekfor2d.di.modules

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import ru.pet.geek.data.remote.GeekClient
import ru.pet.geek.data.remote.JikanClient
import ru.pet.geek.geekfor2d.di.AppScope
import ru.pet.geek.geekfor2d.di.Jikan
import ru.pet.geek.navigationcontroller.NavigationController
import ru.pet.geek.navigationcontroller.NavigationControllerApi

@Module
class AppModule {

    @AppScope
    @Provides
    fun provideNavigationControllerApi(): NavigationControllerApi {
        return NavigationController()
    }

    @AppScope
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @AppScope
    @Jikan
    @Provides
    fun provideJikanClient(
        okHttpClient: OkHttpClient
    ): GeekClient = JikanClient(okHttpClient)

}