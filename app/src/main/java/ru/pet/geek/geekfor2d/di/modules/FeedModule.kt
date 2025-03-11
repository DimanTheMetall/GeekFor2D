package ru.pet.geek.geekfor2d.di.modules

import dagger.Module
import dagger.Provides
import ru.pet.geek.core.LocalResponse
import ru.pet.geek.data.repository.MangaRepository
import ru.pet.geek.domain.entities.dto.MangaRandomCardModel
import ru.pet.geek.features.feed.api.FeedDataApi
import ru.pet.geek.features.feed.api.FeedNavApi
import ru.pet.geek.geekfor2d.di.AppScope
import ru.pet.geek.navigationcontroller.NavigationControllerApi

@Module
class FeedModule {
    @Provides
    @AppScope
    fun providerFeedDataApi(mangaRepository: MangaRepository): FeedDataApi =
        object : FeedDataApi {
            override suspend fun getRandomContent(): LocalResponse<MangaRandomCardModel> = mangaRepository.getRandomMangaContent()
        }

    @Provides
    @AppScope
    fun provideFeedNavApi(navigationControllerApi: NavigationControllerApi): FeedNavApi =
        object : FeedNavApi {
            override fun back() = navigationControllerApi.back()

            override fun goToRandomMangaCard() {
                // TODO
            }

            override fun goToRandomAnimeCard() {
                TODO("Not yet implemented")
            }

            override fun goToRandomCharactersCard() {
                TODO("Not yet implemented")
            }
        }
}
