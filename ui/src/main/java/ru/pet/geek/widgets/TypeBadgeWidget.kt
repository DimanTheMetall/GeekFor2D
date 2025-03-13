package ru.pet.geek.widgets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.pet.geek.entities.ContentTypeUi
import ru.pet.geek.ui.GeekTheme
import ru.pet.geek.ui.R
import ru.pet.geek.utils.PreviewBox
import ru.pet.geek.utils.SpacerWidth

private val shape = RoundedCornerShape(6.dp)

@Composable
fun TypeBadgeWidget(
    modifier: Modifier = Modifier,
    uiInfo: ContentTypeUi,
) {
    val textPrimary = GeekTheme.colors.textPrimary
    Row(
        modifier =
            modifier
                .background(
                    color = uiInfo.getBackground(),
                    shape = shape,
                ).padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Center,
    ) {
        Canvas(modifier = Modifier.size(7.dp)) {
            drawCircle(
                style = Stroke(width = 1.dp.toPx()),
                color = textPrimary,
                radius = this.size.height / 2,
            )
        }
        SpacerWidth(4.dp)
        Text(
            text = uiInfo.text(),
            color = GeekTheme.colors.textPrimary,
            style = GeekTheme.typography.tttSmallRegular,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun ContentTypeUi.getBackground(): Color =
    when (this) {
        ContentTypeUi.Manga -> GeekTheme.colors.blueMedium
        ContentTypeUi.Anime -> GeekTheme.colors.pinkMedium
        ContentTypeUi.Characters -> GeekTheme.colors.orangeMedium
    }

@Composable
private fun ContentTypeUi.text(): String =
    when (this) {
        ContentTypeUi.Manga -> stringResource(R.string.ui_manga)
        ContentTypeUi.Anime -> stringResource(R.string.ui_anime)
        ContentTypeUi.Characters -> stringResource(R.string.ui_characher)
    }

@[Composable Preview]
private fun TypeBadgeWidgetPreview() {
    PreviewBox {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            ContentTypeUi.entries.forEach { type ->
                TypeBadgeWidget(
                    uiInfo = type,
                )
            }
        }
    }
}
