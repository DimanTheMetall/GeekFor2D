package ru.pet.geek.ui

import androidx.compose.runtime.staticCompositionLocalOf
import ru.pet.geek.utils.compositionProviderError

fun ThemeHolder.geekImages(): GeekImages {
    return GeekImagesImpl()
}

interface GeekImages

internal class GeekImagesImpl: GeekImages

val LocalGeekImages = staticCompositionLocalOf<GeekImages> {
    compositionProviderError<GeekImages>()
}