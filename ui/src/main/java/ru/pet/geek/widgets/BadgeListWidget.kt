package ru.pet.geek.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.pet.geek.UiText
import ru.pet.geek.api.Clickable
import ru.pet.geek.ui.R
import ru.pet.geek.utils.PreviewColumn
import ru.pet.geek.utils.SpacerHeight
import ru.pet.geek.utils.mainBlueGradientBrush

private const val CLOSED_ITEM_VISIBLE = 4

interface BadgeListWidgetDataUi : Clickable {
    val allGenres: List<BadgeWidgetDataUi>
    val currentGenresList: List<BadgeWidgetDataUi>
    val isOpen: Boolean
}

data class BadgeListWidgetDataUiImpl(
    override val allGenres: List<BadgeWidgetDataUi>,
    override val isOpen: Boolean,
    private val openWidgetClick: () -> Unit,
    private val closeWidgetClick: () -> Unit,
) : BadgeListWidgetDataUi {
    override val currentGenresList: List<BadgeWidgetDataUi> = getCurrentList()

    override val onClick: () -> Unit = if (isOpen) closeWidgetClick else openWidgetClick
}

private fun BadgeListWidgetDataUi.getCurrentList(): List<BadgeWidgetDataUi> =
    when {
        allGenres.size <= CLOSED_ITEM_VISIBLE -> allGenres
        isOpen ->
            buildList {
                addAll(allGenres)
                add(
                    BadgeWidgetDataUiImpl(
                        text = UiText.ResourcesText(R.string.ui_hide),
                        onClick = onClick,
                    ),
                )
            }

        else ->
            buildList {
                addAll(allGenres.take(CLOSED_ITEM_VISIBLE))
                add(
                    BadgeWidgetDataUiImpl(
                        text = UiText.ResourcesText(R.string.ui_visible_more),
                        onClick = onClick,
                    ),
                )
            }
    }

class BadgeListWidgetPreviewData(
    override val isOpen: Boolean = true,
) : BadgeListWidgetDataUi {
    override val onClick: () -> Unit = {}
    override val allGenres: List<BadgeWidgetDataUi> =
        buildList {
            repeat(20) {
                add(
                    BadgeWidgetPreviewDataUi(),
                )
            }
        }
    override val currentGenresList: List<BadgeWidgetDataUi> = getCurrentList()
}

private val shape = RoundedCornerShape(10.dp)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BadgeListWidget(
        modifier: Modifier = Modifier,
        uiInfo: BadgeListWidgetDataUi,
) {
    FlowRow(
        modifier =
            modifier
                .background(shape = shape, brush = mainBlueGradientBrush)
                .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        maxLines = if (uiInfo.isOpen) Int.MAX_VALUE else 2,
        overflow = FlowRowOverflow.Clip,
    ) {
        uiInfo.currentGenresList.forEach { badgeInfo ->
            BadgeWidget(
                uiInfo = badgeInfo,
            )
        }
    }
}

@Composable
fun BadgeListWidgetLoading(modifier: Modifier = Modifier) {
    Spacer(
        modifier =
            modifier
                .height(36.dp)
                .fillMaxWidth()
                .background(brush = mainBlueGradientBrush, shape = shape),
    )
}

@[Composable Preview]
private fun BadgeListWidgetPreview() {
    PreviewColumn(modifier = Modifier.width(400.dp)) {
        BadgeListWidget(
                modifier = Modifier.fillMaxWidth(),
                uiInfo = BadgeListWidgetPreviewData(isOpen = true),
        )
        SpacerHeight(10.dp)
        BadgeListWidget(
                modifier = Modifier.fillMaxWidth(),
                uiInfo = BadgeListWidgetPreviewData(isOpen = false),
        )
        SpacerHeight(10.dp)
        BadgeListWidgetLoading()
    }
}
