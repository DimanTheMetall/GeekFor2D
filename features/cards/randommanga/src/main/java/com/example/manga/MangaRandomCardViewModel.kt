package com.example.manga

import com.example.cards.basecards.BaseRandomCardViewModel
import com.example.cards.basecards.SuccessUiState
import com.example.manga.api.RandomCardMangaDataApi
import com.example.manga.api.RandomCardMangaNavApi
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.pet.geek.core.LocalResponse
import ru.pet.geek.core.utils.UTCtoUiFormat
import ru.pet.geek.domain.entities.dto.MangaRandomCardModel
import ru.pet.geek.mappers.toUi
import ru.pet.geek.widgets.CalendarTwoLineInfo
import ru.pet.geek.widgets.GradientRatingUiImpl
import ru.pet.geek.widgets.MainInfoWidgetDataUiImpl
import ru.pet.geek.widgets.VolumesChaptersTwoLineInfo

class MangaRandomCardViewModel
    @AssistedInject
    constructor(
        private val navApi: RandomCardMangaNavApi,
        private val dataApi: RandomCardMangaDataApi,
    ) : BaseRandomCardViewModel<MangaRandomCardModel>() {
        init {
            onInit()
        }

        override suspend fun getData(): LocalResponse<MangaRandomCardModel> = dataApi.getRandomCard()

        override fun MangaRandomCardModel.toUi(): SuccessUiState =
            SuccessUiState(
                mainInfo =
                    MainInfoWidgetDataUiImpl(
                        contentTypeUi = this.type.toUi(),
                        imageUrl = this.images.jpg.imageUrl,
                        volumesChapterInfo =
                            VolumesChaptersTwoLineInfo(
                                chapterValue = chapters?.toString(),
                                volumesValue = volumes?.toString(),
                            ),
                        dateInfo =
                            CalendarTwoLineInfo(
                                firstText = publishedModel?.from?.UTCtoUiFormat(),
                                secondText = publishedModel?.to?.UTCtoUiFormat(),
                            ),
                        status = status.toUi(),
                        rating =
                            GradientRatingUiImpl(
                                rating = this.score ?: 0f,
                                ratesClick = scoredBy ?: 0,
                            ),
                        title = titles.toUi(),
                    ),
                synopsys = this.synopsis,
            )

        @AssistedFactory
        interface Factory {
            fun create(): MangaRandomCardViewModel
        }
    }
