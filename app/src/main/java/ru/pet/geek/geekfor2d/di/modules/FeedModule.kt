package ru.pet.geek.geekfor2d.di.modules

import dagger.Module
import dagger.Provides
import ru.pet.geek.features.feed.api.FeedDataApi
import ru.pet.geek.features.feed.api.FeedNavApi
import ru.pet.geek.geekfor2d.di.AppScope
import ru.pet.geek.navigationcontroller.NavigationControllerApi

@Module
class FeedModule {

    @Provides
    @AppScope
    fun providerFeedDataApi(

    ): FeedDataApi {
        return object : FeedDataApi {

        }
    }

    @Provides
    @AppScope
    fun provideFeedNavApi(
        navigationControllerApi: NavigationControllerApi
    ): FeedNavApi {
        return object : FeedNavApi {
            override fun back() = navigationControllerApi.back()
        }
    }

}