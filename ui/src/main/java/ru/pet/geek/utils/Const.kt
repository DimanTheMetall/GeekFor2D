package ru.pet.geek.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import ru.pet.geek.ui.GeekTheme

val mainBlueGradientBrush
    @Composable
    get() = Brush.horizontalGradient(listOf(GeekTheme.colors.transparent, GeekTheme.colors.blueLight))

const val IMAGE_ASPECT_RATION = 0.65f
