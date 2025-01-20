package ru.pet.geek.geekfor2d.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import ru.pet.geek.core.utils.providedError

interface GeekTypography

internal class GeekTypographyImpl: GeekTypography

fun geekTypography(): GeekTypography {
    return GeekTypographyImpl()
}

val LocalGeekTypography = staticCompositionLocalOf<GeekTypography> {
    providedError<GeekTypography>()
}