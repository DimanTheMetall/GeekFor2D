package ru.pet.geek.features.feed

import ru.pet.geek.utils.UiInterface

sealed interface FeedUiState: UiInterface {

    class Loading: FeedUiState {
        override fun equals(other: Any?): Boolean {
            return when {
                other == null -> false
                else -> this::class == other::class
            }
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }

    data class Success(val uiInfo: List<FeedItemUi>): FeedUiState

    data class Error(val e: Throwable): FeedUiState

}