package ru.pet.geek.features.feed

import ru.pet.geek.features.feed.ui.RandomWidgetUi
import ru.pet.geek.utils.UiInterface

sealed interface FeedItemUi: UiInterface {
    data class RandomWidget(
        val uiInfo: RandomWidgetUi
    ): FeedItemUi
}