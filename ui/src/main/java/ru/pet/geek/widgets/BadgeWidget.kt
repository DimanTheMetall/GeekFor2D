package ru.pet.geek.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.pet.geek.UiText
import ru.pet.geek.api.Clickable
import ru.pet.geek.modifierExt.clickable
import ru.pet.geek.ui.GeekTheme
import ru.pet.geek.utils.PreviewBox

interface BadgeWidgetDataUi : Clickable {
    val text: UiText
}

class BadgeWidgetPreviewDataUi : BadgeWidgetDataUi {
    override val text: UiText
        get() = UiText.StringText("preview text")
    override val onClick: () -> Unit = {}
}

data class BadgeWidgetDataUiImpl(
    override val onClick: () -> Unit,
    override val text: UiText,
) : BadgeWidgetDataUi

private val shape = RoundedCornerShape(6.dp)

@Composable
fun BadgeWidget(
    modifier: Modifier = Modifier,
    uiInfo: BadgeWidgetDataUi,
) {
    Text(
        modifier =
            modifier
                .clickable(uiInfo)
                .background(color = GeekTheme.colors.backgroundPrimary, shape = shape)
                .padding(horizontal = 4.dp, vertical = 1.dp),
        text = uiInfo.text.toValue(),
        color = GeekTheme.colors.textPrimary,
        style = GeekTheme.typography.tttSmallRegular,
        maxLines = 1,
    )
}

@[Composable Preview]
private fun BadgeWidgetPreview() {
    PreviewBox {
        BadgeWidget(uiInfo = BadgeWidgetPreviewDataUi())
    }
}
