package com.example.manga

import com.example.cards.basecards.BaseRandomCardViewModel
import com.example.cards.basecards.SuccessUiState
import com.example.manga.api.RandomCardMangaDataApi
import com.example.manga.api.RandomCardMangaNavApi
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.pet.geek.core.LocalResponse
import ru.pet.geek.domain.entities.dto.MangaRandomCardModel
import ru.pet.geek.entities.ContentTypeUi
import ru.pet.geek.mappers.toUi
import ru.pet.geek.widgets.GradientRatingUiImpl
import ru.pet.geek.widgets.MainInfoWidgetDataUiImpl

class MangaRandomCardViewModel
    @AssistedInject
    constructor(
        private val navApi: RandomCardMangaNavApi,
        private val dataApi: RandomCardMangaDataApi,
    ) : BaseRandomCardViewModel<MangaRandomCardModel>() {
        init {
            onInit()
        }

        override suspend fun getRandomData(): LocalResponse<MangaRandomCardModel> = dataApi.getRandomCard()

        override fun MangaRandomCardModel.toUi(): SuccessUiState =
            SuccessUiState(
                mainInfo =
                    MainInfoWidgetDataUiImpl(
                        contentTypeUi = this.type.toUi(default = ContentTypeUi.Manga),
                        imageUrl = this.images.jpg.imageUrl,
                        volumes = this.volumes?.toString(),
                        chapters = this.chapters?.toString(),
                        rating =
                            GradientRatingUiImpl(
                                rating = this.score ?: 0f,
                                ratesClick = scoredBy ?: 0,
                            ),
                        title = title,
                    ),
            )

        @AssistedFactory
        interface Factory {
            fun create(): MangaRandomCardViewModel
        }
    }
