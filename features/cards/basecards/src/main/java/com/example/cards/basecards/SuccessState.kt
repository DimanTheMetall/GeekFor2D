package com.example.cards.basecards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.pet.geek.utils.PreviewBox
import ru.pet.geek.utils.UiInterface
import ru.pet.geek.widgets.CircleButtonInfo
import ru.pet.geek.widgets.CircleIconButton
import ru.pet.geek.widgets.LeftRightButton
import ru.pet.geek.widgets.MainInfoWidget
import ru.pet.geek.widgets.MainInfoWidgetDataPreview
import ru.pet.geek.widgets.MainInfoWidgetDataUi

interface CardSuccessUiState : UiInterface {
    val mainInfo: MainInfoWidgetDataUi
    val previousButton: CircleButtonInfo
    val nextButton: CircleButtonInfo
}

data class SuccessUiState(
    override val mainInfo: MainInfoWidgetDataUi,
    override val previousButton: CircleButtonInfo,
    override val nextButton: CircleButtonInfo,
) : CardSuccessUiState

class CardSuccessStatePreview : CardSuccessUiState {
    override val mainInfo: MainInfoWidgetDataUi = MainInfoWidgetDataPreview()
    override val previousButton: CircleButtonInfo = LeftRightButton.LeftButton(onClick = {})
    override val nextButton: CircleButtonInfo = LeftRightButton.RightButton(onClick = {})
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
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            CircleIconButton(
                modifier = Modifier.size(36.dp),
                uiInfo = uiInfo.previousButton,
            )
            CircleIconButton(
                modifier = Modifier.size(36.dp),
                uiInfo = uiInfo.nextButton,
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
