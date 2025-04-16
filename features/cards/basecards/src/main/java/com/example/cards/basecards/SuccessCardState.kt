package com.example.cards.basecards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import ru.pet.geek.core.GeneralState
import ru.pet.geek.ui.GeekTheme
import ru.pet.geek.utils.PreviewBox
import ru.pet.geek.utils.SpacerHeight
import ru.pet.geek.utils.UiInterface
import ru.pet.geek.widgets.BadgeListWidget
import ru.pet.geek.widgets.BadgeListWidgetDataUi
import ru.pet.geek.widgets.BadgeListWidgetPreviewData
import ru.pet.geek.widgets.MainInfoWidget
import ru.pet.geek.widgets.MainInfoWidgetDataPreview
import ru.pet.geek.widgets.MainInfoWidgetDataUi
import ru.pet.geek.widgets.SlideContentWidget
import ru.pet.geek.widgets.TitledHorisontalCorousel
import ru.pet.geek.widgets.TitledHorisontalCorouselUiInfo

interface CardSuccessUiState : UiInterface {
    val mainInfo: MainInfoWidgetDataUi
    val synopsys: String?
    val genres: BadgeListWidgetDataUi
    val authors: BadgeListWidgetDataUi
}

data class SuccessUiState(
    override val mainInfo: MainInfoWidgetDataUi,
    override val synopsys: String?,
    override val genres: BadgeListWidgetDataUi,
    override val authors: BadgeListWidgetDataUi,
) : CardSuccessUiState

class CardSuccessStatePreview : CardSuccessUiState {
    override val mainInfo: MainInfoWidgetDataUi = MainInfoWidgetDataPreview()
    override val synopsys: String? = LoremIpsum().values.take(4).joinToString(separator = " ")
    override val genres: BadgeListWidgetDataUi = BadgeListWidgetPreviewData()
    override val authors: BadgeListWidgetDataUi = BadgeListWidgetPreviewData()
}

@Composable
fun SuccessCardState(
    modifier: Modifier = Modifier,
    uiInfo: CardSuccessUiState,
    recommendations: GeneralState<TitledHorisontalCorouselUiInfo>,
) {
    Box(
        modifier = modifier,
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter)
                    .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MainInfoWidget(
                modifier = Modifier.fillMaxWidth(),
                uiInfo = uiInfo.mainInfo,
            )
            uiInfo.synopsys?.let { text ->
                SpacerHeight(10.dp)
                SlideContentWidget(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    height = 80.dp,
                ) { inModifier ->
                    Text(
                        modifier = inModifier,
                        text = text,
                        style = GeekTheme.typography.tttSmallRegular,
                        color = GeekTheme.colors.textPrimary,
                        textAlign = TextAlign.Start,
                    )
                }
            }
            if (uiInfo.genres.currentGenresList.isNotEmpty()) {
                SpacerHeight(10.dp)
                BadgeListWidget(
                    modifier = Modifier.fillMaxWidth(),
                    uiInfo = uiInfo.genres,
                )
            }
            if (uiInfo.authors.currentGenresList.isNotEmpty()) {
                SpacerHeight(10.dp)
                BadgeListWidget(
                    modifier = Modifier.fillMaxWidth(),
                    uiInfo = uiInfo.authors,
                )
            }
            if (recommendations is GeneralState.Success<TitledHorisontalCorouselUiInfo>) {
                SpacerHeight(10.dp)
                TitledHorisontalCorousel(
                    modifier = Modifier.padding(horizontal = 6.dp),
                    uiInfo = recommendations.data,
                )
            }
        }
    }
}

@[Composable Preview]
private fun SuccessCardStatePreview() {
    PreviewBox {
        SuccessCardState(
            uiInfo = CardSuccessStatePreview(),
            recommendations = GeneralState.Loading,
        )
    }
}
