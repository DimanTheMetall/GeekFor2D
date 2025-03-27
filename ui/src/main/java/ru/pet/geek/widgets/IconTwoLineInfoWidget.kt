package ru.pet.geek.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.pet.geek.ui.GeekTheme
import ru.pet.geek.ui.R
import ru.pet.geek.utils.PreviewBox
import ru.pet.geek.utils.SpacerHeight
import ru.pet.geek.utils.UiInterface

interface IconTwoLineInfo : UiInterface {
    val firstText: String?
    val secondText: String?

    @get:DrawableRes
    val iconRes: Int
}

data class VolumesChaptersTwoLineInfo(
    val chapterValue: String?,
    val volumesValue: String?,
) : IconTwoLineInfo {
    override val secondText: String? = volumesValue
    override val firstText: String? = chapterValue
    override val iconRes: Int = R.drawable.ui_ic_chapters_volumes
}

data class CalendarTwoLineInfo(
    override val firstText: String?,
    override val secondText: String?,
) : IconTwoLineInfo {
    override val iconRes: Int
        get() = R.drawable.ui_ic_calendar
}

class IconTwoLineInfoPreview : IconTwoLineInfo {
    override val firstText: String = "Текст первой линии"
    override val secondText: String = "Текст второй линии"
    override val iconRes: Int
        get() = R.drawable.ui_ic_star
}

@Composable
fun IconTwoLineInfoWidget(
    modifier: Modifier = Modifier,
    uiInfo: IconTwoLineInfo,
    hideIfEmpty: Boolean = false,
) = IconTwoLineInfoWidget(
    modifier = modifier,
    firstLineInfo = uiInfo.firstText,
    secondLineInfo = uiInfo.secondText,
    iconRes = uiInfo.iconRes,
    hideIfEmpty = hideIfEmpty,
)

@Composable
fun IconTwoLineInfoWidget(
    modifier: Modifier = Modifier,
    hideIfEmpty: Boolean = false,
    @DrawableRes iconRes: Int,
    firstLineInfo: String?,
    secondLineInfo: String?,
) {
    if (hideIfEmpty && firstLineInfo == null && secondLineInfo == null) {
        Unit
    } else {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = modifier.size(44.dp),
                painter = painterResource(iconRes),
                contentDescription = null,
            )

            Column(
                modifier = Modifier.height(40.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
            ) {
                firstLineInfo?.let { text ->
                    Text(
                        text = text,
                        style = GeekTheme.typography.tttSmallRegular,
                        color = GeekTheme.colors.textPrimary,
                        textAlign = TextAlign.Start,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                }
                SpacerHeight(2.dp)
                secondLineInfo?.let { text ->
                    Text(
                        text = text,
                        style = GeekTheme.typography.tttSmallRegular,
                        color = GeekTheme.colors.textPrimary,
                        textAlign = TextAlign.Start,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                }
            }
        }
    }
}

@[Composable Preview]
private fun IconTwoLineInfoWidgetPreview() {
    PreviewBox {
        IconTwoLineInfoWidget(uiInfo = IconTwoLineInfoPreview())
    }
}
