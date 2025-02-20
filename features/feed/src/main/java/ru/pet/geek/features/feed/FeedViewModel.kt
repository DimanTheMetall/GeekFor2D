package ru.pet.geek.features.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.pet.geek.features.feed.api.FeedDataApi
import ru.pet.geek.features.feed.api.FeedNavApi
import ru.pet.geek.features.feed.ui.RandomItemUi
import ru.pet.geek.features.feed.ui.RandomWidgetUiImpl

class FeedViewModel @AssistedInject constructor(
    private val dataApi: FeedDataApi,
    private val navApi: FeedNavApi
) : ViewModel() {

    private val mutableStateUi = MutableStateFlow<FeedUiState>(FeedUiState.Loading())
    val uiState = mutableStateUi.asStateFlow()

    init {
        viewModelScope.launch {
            mutableStateUi.emit(buildSuccessState())
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(): FeedViewModel
    }

    private fun buildSuccessState(): FeedUiState.Success {
        val list = buildList<FeedItemUi> {
            addRandomWidget()
        }
        return FeedUiState.Success(list)
    }

    private fun MutableList<FeedItemUi>.addRandomWidget() {
        val randomList = buildList<RandomItemUi> {
            add(RandomItemUi.Manga(onClick = navApi::goToRandomMangaCard))
            add(RandomItemUi.Anime(onClick = navApi::goToRandomAnimeCard))
            add(RandomItemUi.Characters(onClick = navApi::goToRandomCharactersCard))
        }
        add(
            FeedItemUi.RandomWidget(
                uiInfo = RandomWidgetUiImpl(
                    listUi = randomList
                )
            )
        )
    }
}