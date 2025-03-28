package ru.pet.geek.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.pet.geek.ui.GeekTheme
import ru.pet.geek.ui.R
import ru.pet.geek.utils.PreviewColumn
import ru.pet.geek.utils.SpacerHeight
import ru.pet.geek.utils.TextResHolder

enum class StatusWidgetInfo(
    @StringRes override val textRes: Int,
) : TextResHolder {
    Finished(
        textRes = R.string.ui_status_finished,
    ),
    Publishing(
        textRes = R.string.ui_status_publishing,
    ),
    OnHiatus(
        textRes = R.string.ui_status_hiatus,
    ),
    Discontinued(
        textRes = R.string.ui_status_discontinued,
    ),
    NotYetPublished(
        textRes = R.string.ui_status_not_yet_published,
    ),
    Unknown(
        textRes = R.string.ui_status_unknown,
    ),
    ;

    val color
        @Composable
        get() =
            when (this) {
                Finished, OnHiatus -> GeekTheme.colors.yellowMedium
                Publishing -> GeekTheme.colors.mediumGreen
                NotYetPublished -> GeekTheme.colors.blueLight
                Unknown, Discontinued -> GeekTheme.colors.orangeMedium
            }
}

private val shape = RoundedCornerShape(8.dp)

@Composable
fun StatusWidget(
    modifier: Modifier = Modifier,
    uiInfo: StatusWidgetInfo,
) {
    Text(
        modifier =
            modifier
                .background(color = uiInfo.color, shape = shape)
                .padding(vertical = 2.dp, horizontal = 6.dp),
        text = stringResource(uiInfo.textRes),
        textAlign = TextAlign.Center,
        style = GeekTheme.typography.tttSmallRegular,
        color = GeekTheme.colors.textPrimary,
    )
}

@[Composable Preview]
private fun StatusWidgetPreview() {
    PreviewColumn {
        StatusWidgetInfo.entries.forEach { uiInfo ->
            StatusWidget(uiInfo = uiInfo)
            SpacerHeight(4.dp)
        }
    }
}
