package ru.pet.geek.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import ru.pet.geek.ui.GeekTheme

val mainBlueGradientBrush
    @Composable
    get() = Brush.horizontalGradient(listOf(GeekTheme.colors.transparent, GeekTheme.colors.blueLight))
