package ru.pet.geek.modifierExt

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import ru.pet.geek.api.Clickable
import ru.pet.geek.ui.GeekTheme

fun Modifier.noRippleClickable(enable: Boolean = true, clickable: Clickable) = composed {
    this.clickable(enabled = enable,
        onClick = clickable.onClick,
        indication = null,
        interactionSource = remember { MutableInteractionSource() })
}

fun Modifier.clickable(enable: Boolean = true, clickable: Clickable) = this.clickable(
    enabled = enable,
    onClick = clickable.onClick,
)

private val shimmerLight = listOf(
    Color(0xD0B7B7B7),
    Color(0xD0696969),
    Color(0xD0B7B7B7),
)

private val shimmerDark = listOf(
    Color(0xD01A1A1A),
    Color(0xD0CBCBCB),
    Color(0xD02F2F2F),
)

fun Modifier.shimmerEffect(
    isEnabled: Boolean = true,
) = composed {
    if (isEnabled) {
        val contentCoord = remember { mutableStateOf(IntSize.Zero) }
        val infinity = rememberInfiniteTransition()
        val isDark: Boolean = GeekTheme.colors.isDark
        val startCoordX = infinity.animateFloat(
            initialValue = contentCoord.value.width * -(1f),
            targetValue = contentCoord.value.width * 2f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 700)
            )
        )

        val brush = remember(isDark) {
            if (isDark) {
                shimmerDark
            } else {
                shimmerLight
            }
        }

        background(
            brush = Brush.linearGradient(
                colors = brush,
                start = Offset(x = startCoordX.value, y = 0f),
                end = Offset(
                    x = startCoordX.value + contentCoord.value.width.toFloat(),
                    y = contentCoord.value.height.toFloat()
                )
            ),
        ).onGloballyPositioned { coord ->
            contentCoord.value = coord.size
        }
    } else {
        this
    }
}


