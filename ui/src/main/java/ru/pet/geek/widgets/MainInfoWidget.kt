package ru.pet.geek.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import ru.pet.geek.utils.PreviewColumn
import ru.pet.geek.utils.SpacerHeight
import ru.pet.geek.utils.SpacerWidth
import ru.pet.geek.utils.UiInterface

interface MainInfoWidgetDataUi : UiInterface {
    val contentTypeUi: ContentTypeUi
    val imageUrl: String
    val volumesChapterInfo: IconTwoLineInfo
    val dateInfo: IconTwoLineInfo
    val rating: GradientRatingUi
    val title: String
    val status: StatusWidgetInfo
}

data class MainInfoWidgetDataUiImpl(
    override val contentTypeUi: ContentTypeUi,
    override val imageUrl: String,
    override val dateInfo: IconTwoLineInfo,
    override val volumesChapterInfo: IconTwoLineInfo,
    override val rating: GradientRatingUi,
    override val title: String,
    override val status: StatusWidgetInfo,
) : MainInfoWidgetDataUi

class MainInfoWidgetDataPreview(
    override val dateInfo: IconTwoLineInfo = IconTwoLineInfoPreview(),
    override val volumesChapterInfo: IconTwoLineInfo = IconTwoLineInfoPreview(),
) : MainInfoWidgetDataUi {
    override val contentTypeUi: ContentTypeUi = ContentTypeUi.Manga
    override val imageUrl: String = ""
    override val status: StatusWidgetInfo = StatusWidgetInfo.Finished
    override val rating: GradientRatingUi = GradientRatingUiImpl(rating = 2.4f, ratesClick = 1232)
    override val title: String = "Some title name for some manga title for long long long text text xtext"
}

private val shape = RoundedCornerShape(10.dp)
private val gradientBrush
    @Composable
    get() = Brush.horizontalGradient(listOf(GeekTheme.colors.transparent, GeekTheme.colors.blueLight))

private val height = 274.dp

@Composable
fun MainInfoWidgetLoading(modifier: Modifier = Modifier) {
    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .height(height)
                .background(shape = shape, brush = gradientBrush),
    )
}

@Composable
fun MainInfoWidget(
    modifier: Modifier = Modifier,
    uiInfo: MainInfoWidgetDataUi,
) = MainInfoWidget(
    modifier = modifier,
    imageUrl = uiInfo.imageUrl,
    contentTypeUi = uiInfo.contentTypeUi,
    title = uiInfo.title,
    volumes = uiInfo.volumesChapterInfo.firstText,
    chapters = uiInfo.volumesChapterInfo.secondText,
    ratingInfo = uiInfo.rating,
    chaptersVolumesInfoIconRes = uiInfo.volumesChapterInfo.iconRes,
    startDate = uiInfo.dateInfo.firstText,
    endDate = uiInfo.dateInfo.secondText,
    status = uiInfo.status,
)

@Composable
fun MainInfoWidget(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentTypeUi: ContentTypeUi,
    title: String,
    ratingInfo: GradientRatingUi,
    @DrawableRes chaptersVolumesInfoIconRes: Int = R.drawable.ui_ic_chapters_volumes,
    volumes: String?,
    chapters: String?,
    startDate: String?,
    endDate: String?,
    @DrawableRes dateIconRes: Int = R.drawable.ui_ic_calendar,
    status: StatusWidgetInfo,
) {
    Row(
        modifier =
            modifier
                .height(height)
                .background(brush = gradientBrush, shape = shape)
                .padding(10.dp),
        horizontalArrangement = Arrangement.Absolute.Left,
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            ShimmerAsynhImage(
                modifier = Modifier.height(220.dp).aspectRatio(0.65f),
                model = imageUrl,
            )
            TypeBadgeWidget(
                uiInfo = contentTypeUi,
            )
        }
        SpacerWidth(10.dp)
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.align(Alignment.TopCenter),
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

                IconTwoLineInfoWidget(
                    iconRes = chaptersVolumesInfoIconRes,
                    firstLineInfo = contentTypeUi.toChaptersText(chapters),
                    secondLineInfo = contentTypeUi.toVolumesText(volumes),
                    hideIfEmpty = true,
                )
                IconTwoLineInfoWidget(
                    firstLineInfo = startDate,
                    secondLineInfo = endDate,
                    iconRes = dateIconRes,
                    hideIfEmpty = true,
                )
            }
            StatusWidget(
                modifier = Modifier.align(Alignment.BottomCenter),
                uiInfo = status,
            )
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
    PreviewColumn {
        MainInfoWidget(
            modifier = Modifier.width(400.dp),
            uiInfo = MainInfoWidgetDataPreview(),
        )
        SpacerHeight(10.dp)
        MainInfoWidgetLoading()
    }
}
