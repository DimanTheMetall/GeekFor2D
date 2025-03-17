package com.example.cards.basecards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.pet.geek.utils.PreviewBox
import ru.pet.geek.utils.UiInterface
import ru.pet.geek.widgets.MainInfoWidget
import ru.pet.geek.widgets.MainInfoWidgetDataPreview
import ru.pet.geek.widgets.MainInfoWidgetDataUi

interface CardSuccessUiState : UiInterface {
    val mainInfo: MainInfoWidgetDataUi
}

data class SuccessUiState(
    override val mainInfo: MainInfoWidgetDataUi,
) : CardSuccessUiState

class CardSuccessStatePreview : CardSuccessUiState {
    override val mainInfo: MainInfoWidgetDataUi = MainInfoWidgetDataPreview()
}

@Composable
fun SuccessCardState(
    modifier: Modifier = Modifier,
    uiInfo: CardSuccessUiState,
) {
    Box(
        modifier = modifier,
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MainInfoWidget(
                modifier = Modifier.fillMaxWidth(),
                uiInfo = uiInfo.mainInfo,
            )
        }
    }
}

@[Composable Preview]
private fun SuccessCardStatePreview() {
    PreviewBox {
        SuccessCardState(
            uiInfo = CardSuccessStatePreview(),
        )
    }
}
