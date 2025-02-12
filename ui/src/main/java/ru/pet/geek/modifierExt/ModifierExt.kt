package ru.pet.geek.modifierExt

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import ru.pet.geek.api.Clickable

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


