package ru.pet.geek.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.pet.geek.entities.ContentTypeUi
import ru.pet.geek.ui.GeekTheme
import ru.pet.geek.ui.R
import ru.pet.geek.utils.PreviewBox
import ru.pet.geek.utils.SpacerHeight
import ru.pet.geek.utils.SpacerWidth
import ru.pet.geek.utils.UiInterface

interface MainInfoWidgetDataUi : UiInterface {
    val contentTypeUi: ContentTypeUi
    val imageUrl: String
    val volumes: String?
    val chapters: String?
    val rating: GradientRatingUi
    val title: String
}

data class MainInfoWidgetDataUiImpl(
    override val contentTypeUi: ContentTypeUi,
    override val imageUrl: String,
    override val volumes: String?,
    override val chapters: String?,
    override val rating: GradientRatingUi,
    override val title: String,
) : MainInfoWidgetDataUi

class MainInfoWidgetDataPreview : MainInfoWidgetDataUi {
    override val contentTypeUi: ContentTypeUi = ContentTypeUi.Manga
    override val imageUrl: String = ""
    override val volumes: String? = "1"
    override val chapters: String? = "2"
    override val rating: GradientRatingUi = GradientRatingUiImpl(rating = 2.4f, ratesClick = 1232)
    override val title: String = "Some title name for some manga title for long long long text text xtext"
}

private val shape = RoundedCornerShape(10.dp)

@Composable
fun MainInfoWidget(
    modifier: Modifier = Modifier,
    uiInfo: MainInfoWidgetDataUi,
) = MainInfoWidget(
    modifier = modifier,
    imageUrl = uiInfo.imageUrl,
    contentTypeUi = uiInfo.contentTypeUi,
    title = uiInfo.title,
    volumes = uiInfo.volumes,
    chapters = uiInfo.chapters,
    ratingInfo = uiInfo.rating,
)

@Composable
fun MainInfoWidget(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentTypeUi: ContentTypeUi,
    title: String,
    ratingInfo: GradientRatingUi,
    volumes: String?,
    chapters: String?,
) {
    val gradientBrush = Brush.horizontalGradient(listOf(GeekTheme.colors.transparent, GeekTheme.colors.blueLight))

    Row(
        modifier =
            modifier
                .background(brush = gradientBrush, shape = shape)
                .padding(10.dp),
        horizontalArrangement = Arrangement.Absolute.Left,
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ShimmerAsynhImage(
                modifier = Modifier.height(220.dp).aspectRatio(0.65f),
                model = imageUrl,
            )
            SpacerHeight(4.dp)
            TypeBadgeWidget(
                uiInfo = contentTypeUi,
            )
        }
        SpacerWidth(10.dp)
        Column(
            modifier = Modifier.fillMaxWidth().weight(weight = 1f, fill = true),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                modifier = Modifier.height((20 * 2).dp).fillMaxWidth(),
                text = title,
                style = GeekTheme.typography.tttSmallBold,
                color = GeekTheme.colors.textPrimary,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
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
private fun ContentTypeUi.toVolumesText(volumes: String?): String? =
    when (this) {
        ContentTypeUi.Manga -> volumes?.let { stringResource(R.string.ui_manga_volumes, volumes) }
        ContentTypeUi.Anime -> volumes?.let { stringResource(R.string.ui_anime_volumes, volumes) }
        ContentTypeUi.Characters -> null
    }

@Composable
private fun ContentTypeUi.toChaptersText(chapters: String?): String? =
    when (this) {
        ContentTypeUi.Manga -> chapters?.let { stringResource(R.string.ui_manga_chapters, chapters) }
        ContentTypeUi.Anime -> chapters?.let { stringResource(R.string.ui_anime_chapters, chapters) }
        ContentTypeUi.Characters -> null
    }

@[Composable Preview]
private fun MainInfoWidgetPreview() {
    GeekTheme {
        PreviewBox {
            MainInfoWidget(
                modifier = Modifier.width(400.dp),
                uiInfo = MainInfoWidgetDataPreview(),
            )
        }
    }
}
