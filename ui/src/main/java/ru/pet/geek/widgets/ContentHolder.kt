package ru.pet.geek.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import ru.pet.geek.api.Clickable
import ru.pet.geek.entities.FavoriteStatusUi
import ru.pet.geek.modifierExt.clickable
import ru.pet.geek.ui.GeekTheme
import ru.pet.geek.ui.R
import ru.pet.geek.utils.IMAGE_ASPECT_RATION
import ru.pet.geek.utils.PreviewBox
import ru.pet.geek.utils.SpacerWidth
import ru.pet.geek.utils.UiInterface

private val gradient
    @Composable
    get() =
        Brush.horizontalGradient(
            colors = listOf(GeekTheme.colors.pinkLight, GeekTheme.colors.blueLight),
            tileMode = TileMode.Clamp,
        )

private val IMAGE_HEIGHT = 180.dp
private val IMAGE_WIDTH = IMAGE_HEIGHT * IMAGE_ASPECT_RATION

interface ContentHolderInfoUi : Clickable {
    val imageUrl: String
    val contentInfo: ContentInfoUi
}

data class ContentHolderInfoUiImpl(
    override val onClick: () -> Unit,
    override val imageUrl: String,
    override val contentInfo: ContentInfoUi,
) : ContentHolderInfoUi

class ContentHolderInfoUiPreview : ContentHolderInfoUi {
    override val onClick: () -> Unit = {}
    override val imageUrl: String = ""
    override val contentInfo: ContentInfoUi = ContentInfoUiPreview()
}

// ContentInfoUi

interface ContentInfoUi : UiInterface {
    val title: String
    val favoriteStatusInfo: FavoriteButtonInfoUi
}

data class ContentInfoUiImpl(
    override val title: String,
    override val favoriteStatusInfo: FavoriteButtonInfoUi,
) : ContentInfoUi

class ContentInfoUiPreview : ContentInfoUi {
    override val title: String = LoremIpsum().values.take(2).joinToString(separator = " ")
    override val favoriteStatusInfo: FavoriteButtonInfoUi = FavoriteButtonInfoUiPreview()
}

// FavoriteButtonInfoUi

interface FavoriteButtonInfoUi : Clickable {
    val status: FavoriteStatusUi
}

data class FavoriteButtonInfoUiImpl(
    override val onClick: () -> Unit,
    override val status: FavoriteStatusUi,
) : FavoriteButtonInfoUi

class FavoriteButtonInfoUiPreview(
    override val status: FavoriteStatusUi = FavoriteStatusUi.FAVORITE,
) : FavoriteButtonInfoUi {
    override val onClick: () -> Unit = {}
}

@Composable
fun ContentHolder(
    modifier: Modifier = Modifier,
    uiInfo: ContentHolderInfoUi,
) {
    Column(modifier = modifier) {
        ShimmerAsynhImage(
            modifier =
                Modifier
                    .height(IMAGE_HEIGHT)
                    .aspectRatio(IMAGE_ASPECT_RATION)
                    .clickable(clickable = uiInfo),
            model = uiInfo.imageUrl,
        )
        ContentInfo(
            modifier = Modifier.width(IMAGE_WIDTH),
            uiInfo = uiInfo.contentInfo,
        )
    }
}

@Composable
private fun FavoriteStatusUi.getButtonPainter(): Painter =
    when (this) {
        FavoriteStatusUi.UNFAVORITE -> painterResource(R.drawable.ui_ic_empty_heart)
        FavoriteStatusUi.FAVORITE -> painterResource(R.drawable.ui_ic_heart)
    }

@Composable
private fun FavoriteStatusUi.getButtonColorFilter(): ColorFilter =
    when (this) {
        FavoriteStatusUi.UNFAVORITE -> ColorFilter.tint(color = GeekTheme.colors.iconPrimary)
        FavoriteStatusUi.FAVORITE -> ColorFilter.tint(color = GeekTheme.colors.redMedium)
    }

private val shape = RoundedCornerShape(bottomStart = 40f, bottomEnd = 10f)

@Composable
private fun ContentInfo(
    modifier: Modifier = Modifier,
    uiInfo: ContentInfoUi,
) {
    Row(
        modifier =
            modifier
                .background(brush = gradient, shape = shape)
                .padding(horizontal = 6.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier =
                Modifier
                    .size(16.dp)
                    .clickable(clickable = uiInfo.favoriteStatusInfo),
            painter = uiInfo.favoriteStatusInfo.status.getButtonPainter(),
            contentDescription = null,
            colorFilter = uiInfo.favoriteStatusInfo.status.getButtonColorFilter(),
        )
        SpacerWidth(4.dp)
        Text(
            text = uiInfo.title,
            color = GeekTheme.colors.textPrimary,
            style = GeekTheme.typography.tttSmallRegular,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@[Composable Preview]
private fun ContentHolderPreview() {
    PreviewBox {
        ContentHolder(
            uiInfo = ContentHolderInfoUiPreview(),
        )
    }
}
