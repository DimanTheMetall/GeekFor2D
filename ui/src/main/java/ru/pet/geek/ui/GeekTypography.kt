package ru.pet.geek.ui

import androidx.compose.runtime.staticCompositionLocalOf
import ru.pet.geek.utils.compositionProviderError


interface GeekTypography

internal class GeekTypographyImpl: GeekTypography

fun geekTypography(): GeekTypography {
    return GeekTypographyImpl()
}

val LocalGeekTypography = staticCompositionLocalOf<GeekTypography> {
    compositionProviderError<GeekTypography>()
}