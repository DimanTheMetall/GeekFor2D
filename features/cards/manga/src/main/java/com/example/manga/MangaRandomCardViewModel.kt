package com.example.manga

import com.example.cards.basecards.BaseRandomCardViewModel
import com.example.cards.basecards.SuccessUiState
import com.example.manga.api.RandomCardMangaDataApi
import com.example.manga.api.RandomCardMangaNavApi
import dagger.assisted.AssistedFactory
import ru.pet.geek.core.LocalResponse
import ru.pet.geek.domain.entities.dto.MangaRandomCardModel

class MangaRandomCardViewModel(
    private val navApi: RandomCardMangaNavApi,
    private val dataApi: RandomCardMangaDataApi,
) : BaseRandomCardViewModel<MangaRandomCardModel>() {
    override suspend fun getRandomData(): LocalResponse<MangaRandomCardModel> {
        TODO("Not yet implemented")
    }

    override fun MangaRandomCardModel.toUi(): SuccessUiState {
        TODO("Not yet implemented")
    }

    @AssistedFactory
    interface Factory {
        fun create(): MangaRandomCardViewModel
    }
}
