package ru.pet.geek.geekfor2d.di.modules

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ru.pet.geek.data.BuildConfig
import ru.pet.geek.data.remote.GeekClient
import ru.pet.geek.data.remote.JikanClient
import ru.pet.geek.geekfor2d.di.AppScope
import ru.pet.geek.geekfor2d.di.Jikan
import ru.pet.geek.navigationcontroller.NavigationController
import ru.pet.geek.navigationcontroller.NavigationControllerApi

@Module
class AppModule {


    @[AppScope Provides]
    fun provideNavigationControllerApi(): NavigationControllerApi {
        return NavigationController()
    }

    @[AppScope Provides]
    fun provideLoggerInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
    }

    @[AppScope Provides]
    fun provideOkHttp(
        //TODO implement own logger, because json so sadly viewed
        logger: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    @[AppScope Provides Jikan]
    fun provideJikanClient(
        okHttpClient: OkHttpClient
    ): GeekClient = JikanClient(okHttpClient)

}