package ru.pet.geek.geekfor2d.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import ru.pet.geek.core.utils.providedError

fun ThemeHolder.geekPalette(): GeekColors {
    return GeekColorsImpl()
}

interface GeekColors

internal class GeekColorsImpl: GeekColors

val LocalGeekColors = staticCompositionLocalOf<GeekColors> {
    providedError<GeekColors>()
}