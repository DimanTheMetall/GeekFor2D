package ru.pet.geek.features.feed.ui

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.pet.geek.UiText
import ru.pet.geek.api.Clickable
import ru.pet.geek.modifierExt.noRippleClickable
import ru.pet.geek.ui.GeekTheme
import ru.pet.geek.utils.SpacerHeight
import ru.pet.geek.utils.UiInterface

sealed interface RandomItemUi : Clickable {
    val text: UiText

    @get:Composable
    val backgroundColor: Color

    data class Manga(
        override val onClick: () -> Unit = {},
    ) : RandomItemUi {
        override val text: UiText = UiText.ResourcesText(ru.pet.geek.ui.R.string.ui_manga)

        override val backgroundColor: Color
            @Composable get() = GeekTheme.colors.violetLight
    }

    data class Anime(
        override val onClick: () -> Unit = {},
    ) : RandomItemUi {
        override val text: UiText = UiText.ResourcesText(ru.pet.geek.ui.R.string.ui_anime)

        override val backgroundColor: Color
            @Composable get() = GeekTheme.colors.pinkMedium
    }

    data class Characters(
        override val onClick: () -> Unit = {},
    ) : RandomItemUi {
        override val text: UiText = UiText.ResourcesText(ru.pet.geek.ui.R.string.ui_charachers)

        override val backgroundColor: Color
            @Composable get() = GeekTheme.colors.blueMedium
    }
}

interface RandomWidgetUi : UiInterface {
    val listUi: List<RandomItemUi>
}

data class RandomWidgetUiImpl(
    override val listUi: List<RandomItemUi>,
) : RandomWidgetUi

@Composable
internal fun RandomsWidget(
    modifier: Modifier = Modifier,
    uiInfo: RandomWidgetUi,
) {
    val shape = remember { RoundedCornerShape(topEnd = 26.dp, bottomStart = 26.dp) }
    Column(
        modifier =
            modifier
                .background(
                    shape = shape,
                    color = GeekTheme.colors.backgroundModalSemiTransparent,
                ).border(
                    border = BorderStroke(width = 2.dp, color = GeekTheme.colors.greyscale500),
                    shape = shape,
                ).padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(ru.pet.geek.ui.R.string.ui_random),
            color = GeekTheme.colors.textPrimary,
            style = GeekTheme.typography.tttSmallBold,
            textAlign = TextAlign.Center,
        )
        SpacerHeight(10.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            uiInfo.listUi.forEach { item ->
                RandomItem(
                    uiInfo = item,
                    width = 46.dp,
                )
            }
        }
    }
}

@[
Composable Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "dark") Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "light",
)
]
private fun RandomsWidgetPreview() {
    GeekTheme {
        Box(
            modifier =
                Modifier
                    .background(GeekTheme.colors.backgroundPrimary)
                    .padding(20.dp),
        ) {
            RandomsWidget(
                modifier = Modifier.padding(20.dp),
                uiInfo =
                    RandomWidgetUiImpl(
                        listOf(
                            RandomItemUi.Manga(),
                            RandomItemUi.Anime(),
                            RandomItemUi.Characters(),
                        ),
                    ),
            )
        }
    }
}

@Composable
private fun RandomItem(
    uiInfo: RandomItemUi,
    modifier: Modifier = Modifier,
    width: Dp = 36.dp,
) {
    val chatText = uiInfo.text.toValue()

    Column(
        modifier = modifier.noRippleClickable(clickable = uiInfo),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier =
                Modifier
                    .width(width)
                    .background(color = uiInfo.backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            chatText.forEach { char ->
                Text(
                    text = char.toString(),
                    color = GeekTheme.colors.textPrimary,
                    style = GeekTheme.typography.tttLargeBold,
                )
            }
        }

        Flag(
            modifier =
                Modifier
                    .size(width)
                    .offset(y = (-1).dp),
            color = uiInfo.backgroundColor,
        )
    }
}

@[Composable Preview]
private fun RandomItemPreview() {
    GeekTheme {
        RandomItem(
            modifier =
                Modifier
                    .width(45.dp)
                    .background(Color.White),
            uiInfo = RandomItemUi.Manga(),
        )
    }
}

@Composable
private fun Flag(
    modifier: Modifier = Modifier,
    color: Color,
) {
    Canvas(modifier = modifier) {
        val path =
            Path().apply {
                lineTo(x = size.width, y = 0f)
                lineTo(x = size.width * 0.8f, y = size.height)
                lineTo(x = size.width / 2, y = size.height / 2)
                lineTo(x = size.width * 0.2f, y = size.height)
                lineTo(x = 0f, 0f)
                close()
            }

        drawPath(path = path, color = color, style = Fill)
    }
}
