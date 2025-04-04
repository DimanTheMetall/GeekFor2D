package ru.pet.geek.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.pet.geek.utils.PreviewBox
import ru.pet.geek.utils.dpToPx
import ru.pet.geek.utils.mainBlueGradientBrush
import kotlin.math.roundToInt

private val shape = RoundedCornerShape(10.dp)

@Composable
fun SliderContentWidgetLoading(
    modifier: Modifier = Modifier,
    height: Dp = 120.dp,
) {
    Spacer(
        modifier =
            modifier
                .height(height)
                .fillMaxWidth()
                .background(brush = mainBlueGradientBrush, shape = shape),
    )
}

@Composable
fun SlideContentWidget(
    modifier: Modifier = Modifier,
    height: Dp = 120.dp,
    content: @Composable (Modifier) -> Unit,
) {
    val maxPx = height.dpToPx()

    val scrollState = rememberScrollState()
    val scrollPercent = remember { mutableFloatStateOf(0f) }

    val currentDragPosition = remember { mutableFloatStateOf(0f) }
    val dragState =
        rememberDraggableState { delta ->
            currentDragPosition.floatValue = (currentDragPosition.floatValue + delta)
        }

    LaunchedEffect(currentDragPosition.floatValue, maxPx) {
        scrollPercent.floatValue = currentDragPosition.floatValue / maxPx
    }

    LaunchedEffect(scrollPercent.floatValue) {
        val scrollToValue = scrollState.maxValue * scrollPercent.floatValue
        scrollState.scrollTo(scrollToValue.roundToInt())
    }

    LaunchedEffect(scrollState.value) {
        scrollPercent.floatValue = scrollState.value.toFloat() / scrollState.maxValue.coerceAtLeast(1)
    }

    Row(
        modifier =
            modifier
                .background(shape = shape, brush = mainBlueGradientBrush)
                .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
    ) {
        content(
            Modifier
                .height(height = height)
                .weight(0.9f, false)
                .verticalScroll(state = scrollState),
        )
        SliderWidget(
            modifier = Modifier.padding(start = 10.dp),
            height = height,
            dragState = dragState,
            scrollPercent = scrollPercent,
            cornerRadius = 8.dp,
        )
    }
}

@[Preview Composable]
private fun SlideContentWidgetPreview() {
    PreviewBox {
        SlideContentWidget(
            modifier = Modifier.width(300.dp),
            height = 120.dp,
        ) {
            Text(
                modifier = it,
                text = LoremIpsum().values.take(4).joinToString(separator = " "),
            )
        }
    }
}
