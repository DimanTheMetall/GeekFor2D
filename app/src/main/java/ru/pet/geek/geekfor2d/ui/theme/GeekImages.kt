package ru.pet.geek.geekfor2d.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import ru.pet.geek.core.utils.providedError

fun ThemeHolder.geekImages(): GeekImages {
    return GeekImagesImpl()
}

interface GeekImages

internal class GeekImagesImpl: GeekImages

val LocalGeekImages = staticCompositionLocalOf<GeekImages> {
    providedError<GeekImages>()
}