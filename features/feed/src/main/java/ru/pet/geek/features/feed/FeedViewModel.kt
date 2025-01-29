package ru.pet.geek.features.feed

import androidx.lifecycle.ViewModel
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.pet.geek.features.feed.api.FeedDataApi
import ru.pet.geek.features.feed.api.FeedNavApi

class FeedViewModel @AssistedInject constructor(
    private val dataApi: FeedDataApi,
    private val navApi: FeedNavApi
): ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(): FeedViewModel
    }

    fun onBackPress() = navApi.back()
}