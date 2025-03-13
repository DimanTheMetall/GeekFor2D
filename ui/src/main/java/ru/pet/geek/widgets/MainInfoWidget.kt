package ru.pet.geek.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.pet.geek.entities.ContentTypeUi
import ru.pet.geek.ui.GeekTheme
import ru.pet.geek.ui.R
import ru.pet.geek.utils.PreviewBox
import ru.pet.geek.utils.SpacerHeight

private val shape = RoundedCornerShape(10.dp)

@Composable
fun MainInfoWidget(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentTypeUi: ContentTypeUi,
    title: String,
    ratingInfo: GradientRatingUi,
    volumes: String,
    chapters: String,
) {
    val gradientBrush = Brush.horizontalGradient(listOf(GeekTheme.colors.transparent, GeekTheme.colors.blueLight))

    Row(
        modifier =
            modifier
                .background(brush = gradientBrush, shape = shape)
                .padding(10.dp),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
    ) {
        Column(
            modifier = Modifier.weight(fill = false, weight = 1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ShimmerAsynhImage(
                modifier = Modifier.size(width = 70.dp, height = 160.dp),
                model = imageUrl,
            )
            SpacerHeight(4.dp)
            TypeBadgeWidget(
                uiInfo = contentTypeUi,
            )
        }
        Column(
            modifier = Modifier.weight(0.9f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                modifier = Modifier,
                text = title,
                style = GeekTheme.typography.tttSmallBold,
                color = GeekTheme.colors.textPrimary,
                textAlign = TextAlign.Center,
            )
            GradientRatingWidget(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(20.dp),
                uiInfo = ratingInfo,
            )
            contentTypeUi.toVolumesText(volumes = volumes)?.let {
                Text(
                    text = it,
                    style = GeekTheme.typography.tttSmallRegular,
                    color = GeekTheme.colors.textPrimary,
                )
            }
            contentTypeUi.toChaptersText(chapters = chapters)?.let {
                Text(
                    text = it,
                    style = GeekTheme.typography.tttSmallRegular,
                    color = GeekTheme.colors.textPrimary,
                )
            }
        }
    }
}

@Composable
private fun ContentTypeUi.toVolumesText(volumes: String): String? =
    when (this) {
        ContentTypeUi.Manga -> stringResource(R.string.ui_manga_volumes, volumes)
        ContentTypeUi.Anime -> stringResource(R.string.ui_anime_volumes, volumes)
        ContentTypeUi.Characters -> null
    }

@Composable
private fun ContentTypeUi.toChaptersText(chapters: String): String? =
    when (this) {
        ContentTypeUi.Manga -> stringResource(R.string.ui_manga_chapters, chapters)
        ContentTypeUi.Anime -> stringResource(R.string.ui_anime_chapters, chapters)
        ContentTypeUi.Characters -> null
    }

@[Composable Preview]
private fun MainInfoWidgetPreview() {
    GeekTheme {
        PreviewBox {
            MainInfoWidget(
                modifier = Modifier.padding(10.dp),
                contentTypeUi = ContentTypeUi.Manga,
                imageUrl = "",
                title = "Some manga title for long long long long long text",
                ratingInfo =
                    GradientRatingUiImpl(
                        rating = 6.4f,
                        ratesClick = 1224,
                    ),
                chapters = "8",
                volumes = "2",
            )
        }
    }
}
