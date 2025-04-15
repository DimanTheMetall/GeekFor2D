package ru.pet.geek.geekfor2d.di.modules

import com.example.manga.api.RandomCardMangaDataApi
import com.example.manga.api.RandomCardMangaNavApi
import dagger.Module
import dagger.Provides
import ru.pet.geek.core.LocalResponse
import ru.pet.geek.data.repository.MangaRepository
import ru.pet.geek.domain.entities.dto.EntryModel
import ru.pet.geek.domain.entities.dto.MangaRandomCardModel
import ru.pet.geek.geekfor2d.di.AppScope
import ru.pet.geek.navigationcontroller.NavigationControllerApi

@Module
class MangaRandomCardModule {
    @AppScope
    @Provides
    fun provideMangaRandomCardDataApi(mangaRepository: MangaRepository): RandomCardMangaDataApi =
        object : RandomCardMangaDataApi {
            override suspend fun getRandomCard(): LocalResponse<MangaRandomCardModel> = mangaRepository.getRandomMangaContent()

            override suspend fun getMangaRecommendations(mangaId: Int): LocalResponse<List<EntryModel>> =
                mangaRepository.getRecommendationsMangaContent(mangaId)
        }

    @AppScope
    @Provides
    fun providerMangaRandomCardNavApi(navController: NavigationControllerApi): RandomCardMangaNavApi =
        object : RandomCardMangaNavApi {
            override fun goBack() = navController.back()
        }
}
