package com.example.cards.basecards

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.pet.geek.VisibilityItem
import ru.pet.geek.VisibilityItemImpl
import ru.pet.geek.utils.PreviewBox
import ru.pet.geek.widgets.CircleButtonInfo
import ru.pet.geek.widgets.CircleIconButton
import ru.pet.geek.widgets.LeftRightButton

interface LeftRightButtonsWidgetState {
    val leftButton: VisibilityItem<CircleButtonInfo>
    val rightButton: VisibilityItem<CircleButtonInfo>
}

@Stable
data class LeftRightButtonsWidgetStateImpl(
    override val leftButton: VisibilityItem<CircleButtonInfo>,
    override val rightButton: VisibilityItem<CircleButtonInfo>,
) : LeftRightButtonsWidgetState

class LeftRightButtonsWidgetStatePreviewState : LeftRightButtonsWidgetState {
    override val leftButton: VisibilityItem<CircleButtonInfo>
        get() = VisibilityItemImpl(item = LeftRightButton.LeftButton(onClick = {}))
    override val rightButton: VisibilityItem<CircleButtonInfo>
        get() = VisibilityItemImpl(item = LeftRightButton.RightButton(onClick = {}))
}

@Composable
fun LeftRightButtonsWidget(
    modifier: Modifier = Modifier,
    uiInfo: LeftRightButtonsWidgetState,
) = LeftRightButtonsWidget(modifier = modifier, leftButtonsState = uiInfo.leftButton, rightButtonsState = uiInfo.rightButton)

private val enterAnimation = fadeIn()
private val exitAnimation = fadeOut()

@Composable
fun LeftRightButtonsWidget(
    modifier: Modifier = Modifier,
    leftButtonsState: VisibilityItem<CircleButtonInfo>,
    rightButtonsState: VisibilityItem<CircleButtonInfo>,
) {
    val arrangment =
        remember {
            mutableStateOf<Arrangement.Horizontal>(Arrangement.Center)
        }
    LaunchedEffect(leftButtonsState, rightButtonsState) {
        val newState =
            when {
                leftButtonsState.isVisible && rightButtonsState.isVisible -> Arrangement.SpaceBetween
                rightButtonsState.isVisible && leftButtonsState.isVisible.not() -> Arrangement.End
                else -> Arrangement.SpaceBetween
            }
        arrangment.value = newState
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
    ) {
        Box {
            androidx.compose.animation.AnimatedVisibility(
                modifier = Modifier,
                visible = leftButtonsState.isVisible,
                exit = exitAnimation,
                enter = enterAnimation,
            ) {
                CircleIconButton(
                    modifier = Modifier.size(36.dp),
                    uiInfo = leftButtonsState.item,
                )
            }
        }

        Box {
            androidx.compose.animation.AnimatedVisibility(
                visible = rightButtonsState.isVisible,
                exit = exitAnimation,
                enter = enterAnimation,
            ) {
                CircleIconButton(
                    modifier = Modifier.size(36.dp),
                    uiInfo = rightButtonsState.item,
                )
            }
        }
    }
}

@[Composable Preview]
private fun LeftRightButtonsWidgetPreview() {
    PreviewBox {
        LeftRightButtonsWidget(uiInfo = LeftRightButtonsWidgetStatePreviewState())
    }
}
