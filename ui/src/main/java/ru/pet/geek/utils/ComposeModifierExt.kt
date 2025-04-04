package ru.pet.geek.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush

fun Modifier.gradient(brush: Brush): Modifier =
    this.drawWithContent {
        drawContent()
        drawRect(brush = brush)
    }

fun Modifier.gradient(
    brush: Brush,
    cornerRadius: CornerRadius,
): Modifier =
    this.drawWithContent {
        drawContent()
        drawRoundRect(brush = brush, cornerRadius = cornerRadius)
    }
