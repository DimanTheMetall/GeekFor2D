package com.example.cards.basecards

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.pet.geek.widgets.BaseErrorScreenWidget

@Composable
fun StateSelector(
    modifier: Modifier = Modifier,
    state: RandomCardUiState,
) {
    AnimatedContent(
        modifier = modifier,
        targetState = state,
        transitionSpec = { fadeIn().togetherWith(fadeOut()) },
    ) { newsState ->
        when (newsState) {
            is RandomCardUiState.Error ->
                BaseErrorScreenWidget(
                    modifier = modifier,
                    e = newsState.e,
                    refreshButton = newsState.uiInfo,
                )

            is RandomCardUiState.Loading -> LoadingCardState(modifier = modifier)
            is RandomCardUiState.Success ->
                SuccessCardState(
                    modifier = modifier,
                    uiInfo = newsState.data,
                )
        }
    }
}
