package ru.pet.geek.widgets

import androidx.annotation.DrawableRes
import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.pet.geek.api.Clickable
import ru.pet.geek.modifierExt.noRippleClickable
import ru.pet.geek.ui.GeekTheme
import ru.pet.geek.ui.R
import ru.pet.geek.utils.PreviewBox

interface CircleButtonInfo : Clickable {
    @get:Composable
    val background: Color

    @get:DrawableRes
    val iconRes: Int

    @get:FloatRange(-360.0, 360.0)
    val angle: Float
}

sealed interface LeftRightButton : CircleButtonInfo {
    data class LeftButton(
        override val onClick: () -> Unit,
    ) : LeftRightButton {
        override val background: Color
            @Composable
            get() = GeekTheme.colors.blueLight
        override val iconRes: Int
            @DrawableRes
            get() = R.drawable.ui_ic_arrow_up

        override val angle: Float
            @FloatRange(-360.0, 360.0)
            get() = -90.0f
    }

    data class RightButton(
        override val onClick: () -> Unit,
    ) : LeftRightButton {
        override val background: Color
            @Composable
            get() = GeekTheme.colors.pinkLight
        override val iconRes: Int
            @DrawableRes
            get() = R.drawable.ui_ic_arrow_up

        override val angle: Float
            @FloatRange(-360.0, 360.0)
            get() = 90.0f
    }
}

@Composable
fun CircleIconButton(
    modifier: Modifier = Modifier,
    uiInfo: CircleButtonInfo,
) {
    Box(
        modifier =
            modifier
                .noRippleClickable(clickable = uiInfo)
                .background(color = uiInfo.background, shape = CircleShape)
                .padding(4.dp),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier =
                Modifier
                    .graphicsLayer {
                        this.rotationZ = uiInfo.angle
                    },
            painter = painterResource(uiInfo.iconRes),
            contentDescription = null,
        )
    }
}

@[Composable Preview]
private fun CircleIconButtonPreview() {
    PreviewBox {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            CircleIconButton(
                modifier = Modifier.size(26.dp),
                uiInfo = LeftRightButton.LeftButton(onClick = {}),
            )
            CircleIconButton(
                modifier = Modifier.size(26.dp),
                uiInfo = LeftRightButton.RightButton(onClick = {}),
            )
        }
    }
}
