package ru.pet.geek.ui

import androidx.compose.runtime.staticCompositionLocalOf
import ru.pet.geek.utils.compositionProviderError

fun ThemeHolder.geekPalette(): GeekColors {
    return GeekColorsImpl()
}

interface GeekColors

internal class GeekColorsImpl: GeekColors

val LocalGeekColors = staticCompositionLocalOf<GeekColors> {
    compositionProviderError<GeekColors>()
}