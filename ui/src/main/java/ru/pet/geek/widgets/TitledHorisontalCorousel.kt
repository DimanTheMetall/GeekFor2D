package ru.pet.geek.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.pet.geek.UiText
import ru.pet.geek.api.Clickable
import ru.pet.geek.ui.GeekTheme
import ru.pet.geek.ui.R
import ru.pet.geek.utils.ListUiDecorator
import ru.pet.geek.utils.PreviewBox
import ru.pet.geek.utils.SpacerHeight

const val ITEMS_FOR_MORE_VISIBLE = 18

fun List<*>.isMoreVisibleHorisontalCorousel(): Boolean = this.size > ITEMS_FOR_MORE_VISIBLE

interface TitledHorisontalCorouselUiInfo : Clickable {
    val itemsList: ListUiDecorator<ContentHolderInfoUi>
    val title: UiText
    val isMoreVisible: Boolean
}

data class TitledHorisontalCorouselUiInfoImpl(
    override val itemsList: ListUiDecorator<ContentHolderInfoUi>,
    override val title: UiText,
    override val isMoreVisible: Boolean,
    override val onClick: () -> Unit,
) : TitledHorisontalCorouselUiInfo

class TitledHorisontalCorouselUiInfoPreview : TitledHorisontalCorouselUiInfo {
    override val itemsList: ListUiDecorator<ContentHolderInfoUi> =
        ListUiDecorator(
            items =
                buildList {
                    repeat(8) {
                        add(ContentHolderInfoUiPreview())
                    }
                },
        )
    override val title: UiText = UiText.StringText("Title")
    override val isMoreVisible: Boolean = true
    override val onClick: () -> Unit = {}
}

@Composable
fun TitledHorisontalCorousel(
    modifier: Modifier = Modifier,
    uiInfo: TitledHorisontalCorouselUiInfo,
) {
    Column(modifier = modifier) {
        TitleInfo(
            modifier = Modifier.fillMaxWidth(),
            isMoreVisible = uiInfo.isMoreVisible,
            title = uiInfo.title,
            onMoreClick = uiInfo.onClick,
        )
        SpacerHeight(6.dp)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(
                items = uiInfo.itemsList.items,
            ) { content ->
                ContentHolder(
                    uiInfo = content,
                )
            }
        }
    }
}

@Composable
private fun TitleInfo(
    modifier: Modifier = Modifier,
    isMoreVisible: Boolean,
    title: UiText,
    onMoreClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = title.toValue(),
            style = GeekTheme.typography.tttMediumRegular,
            color = GeekTheme.colors.textPrimary,
        )
        if (isMoreVisible) {
            Image(
                modifier =
                    Modifier
                        .clickable(onClick = onMoreClick),
                painter = painterResource(R.drawable.ui_ic_arrow_right),
                contentDescription = null,
            )
        }
    }
}

@[Composable Preview]
private fun TitledHorisontalCorouselPreview() {
    PreviewBox {
        TitledHorisontalCorousel(uiInfo = TitledHorisontalCorouselUiInfoPreview())
    }
}
