package com.example.manga

import com.example.cards.basecards.BaseRandomCardViewModel
import com.example.cards.basecards.SuccessUiState
import com.example.manga.api.RandomCardMangaDataApi
import com.example.manga.api.RandomCardMangaNavApi
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import ru.pet.geek.UiText
import ru.pet.geek.core.GeneralState
import ru.pet.geek.core.LocalResponse
import ru.pet.geek.core.mappers.toGeneralState
import ru.pet.geek.core.utils.UTCtoUiFormat
import ru.pet.geek.core.utils.emptyLambda
import ru.pet.geek.domain.entities.dto.EntryModel
import ru.pet.geek.domain.entities.dto.FavoriteState
import ru.pet.geek.domain.entities.dto.MangaRandomCardModel
import ru.pet.geek.mappers.authorsToListBadgesWidgetUi
import ru.pet.geek.mappers.genresToListBadgesWidgetUi
import ru.pet.geek.mappers.toHorisontalCorouselContentHodler
import ru.pet.geek.mappers.toUi
import ru.pet.geek.utils.ListUiDecorator
import ru.pet.geek.widgets.CalendarTwoLineInfo
import ru.pet.geek.widgets.GradientRatingUiImpl
import ru.pet.geek.widgets.MainInfoWidgetDataUiImpl
import ru.pet.geek.widgets.TitledHorisontalCorouselUiInfo
import ru.pet.geek.widgets.TitledHorisontalCorouselUiInfoImpl
import ru.pet.geek.widgets.VolumesChaptersTwoLineInfo
import ru.pet.geek.widgets.isMoreVisibleHorisontalCorousel

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

        override suspend fun getRecommendations(mangaId: Int): GeneralState<List<EntryModel>> =
            dataApi.getMangaRecommendations(mangaId).toGeneralState()

        override suspend fun getFavoritesIdsFlow(): Flow<List<Int>> = dataApi.getFavoriteListIdsFlow()

        override fun List<EntryModel>.toRecommendationsCarouselUI(): TitledHorisontalCorouselUiInfo {
            val list =
                this.map {
                    it.toHorisontalCorouselContentHodler(
                        // TODO
                        onImageClick = ::emptyLambda,
                        // TODO
                        onFavoriteButtonClick = ::emptyLambda,
                        favoriteState = FavoriteState(isFavorite = false),
                    )
                }
            return TitledHorisontalCorouselUiInfoImpl(
                title = UiText.ResourcesText(ru.pet.geek.ui.R.string.ui_horisontal_corousel_recommendation_title),
                isMoreVisible = this.isMoreVisibleHorisontalCorousel(),
                // TODO implement
                onClick = ::emptyLambda,
                itemsList =
                    ListUiDecorator(
                        items = list,
                    ),
            )
        }

        override fun MangaRandomCardModel.toUi(isOpenGenresWidget: Boolean): SuccessUiState =
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
                genres =
                    genres.genresToListBadgesWidgetUi(
                        isOpen = isOpenGenresWidget,
                        onCloseClick = ::closeGenresWidget,
                        onOpenClick = ::openGenresWidget,
                    ),
                authors =
                    authors.authorsToListBadgesWidgetUi(
                        isOpen = true,
                        onCloseClick = ::emptyLambda,
                        onOpenClick = ::emptyLambda,
                    ),
            )

        @AssistedFactory
        interface Factory {
            fun create(): MangaRandomCardViewModel
        }
    }
