package ru.pet.geek.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import ru.pet.geek.ui.GeekTheme
import ru.pet.geek.utils.PreviewBox
import ru.pet.geek.utils.dpToPx
import kotlin.math.roundToInt

private val colors
    @Composable
    get() = listOf(GeekTheme.colors.backgroundPrimary, GeekTheme.colors.blueLight)

private val circleBrush
    @Composable
    get() = Brush.radialGradient(colors = colors)

@Composable
fun SliderWidget(
    modifier: Modifier = Modifier,
    height: Dp,
    dragState: DraggableState,
    scrollPercent: State<Float>,
    cornerRadius: Dp = 4.dp,
) {
    val cornerRadiusPx = cornerRadius.dpToPx()
    val shape = remember(cornerRadius) { RoundedCornerShape(cornerRadiusPx) }

    val maxPx = height.dpToPx()
    val scrollHeight = remember(maxPx) { maxPx - 2 * cornerRadiusPx }
    Box(
        modifier =
            modifier
                .width(width = cornerRadius * 2)
                .height(height)
                .background(color = GeekTheme.colors.backgroundPrimary, shape = shape),
    ) {
        Spacer(
            modifier =
                Modifier
                    .size(cornerRadius * 2)
                    .offset {
                        println()
                        IntOffset(
                            x = 0,
                            y =
                                (scrollHeight * scrollPercent.value).toInt().coerceIn(
                                    minimumValue = 0,
                                    maximumValue = scrollHeight.roundToInt(),
                                ),
                        )
                    }.background(brush = circleBrush, shape = CircleShape)
                    .border(width = 0.2.dp, color = GeekTheme.colors.greyscale500, shape = CircleShape)
                    .draggable(
                        state = dragState,
                        orientation = Orientation.Vertical,
                    ),
        )
    }
}

@[Preview Composable]
fun PreviewSliderWidget() {
    PreviewBox {
        val state = remember { mutableFloatStateOf(0.5f) }
        SliderWidget(
            modifier = Modifier.width(width = 8.dp),
            height = 80.dp,
            dragState = rememberDraggableState {},
            scrollPercent = state,
        )
    }
}
