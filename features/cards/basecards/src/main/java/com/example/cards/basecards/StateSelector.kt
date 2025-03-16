package com.example.cards.basecards

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.pet.geek.widgets.BaseErrorScreenWidget

@Composable
fun StateSelector(state: RandomCardUiState) {
    when (state) {
        is RandomCardUiState.Error ->
            BaseErrorScreenWidget(
                modifier = Modifier.fillMaxSize(),
                e = state.e,
                refreshButton = state.uiInfo,
            )

        is RandomCardUiState.Loading -> Unit
        is RandomCardUiState.Success ->
            SuccessCardState(
                uiInfo = state.data,
            )
    }
}
