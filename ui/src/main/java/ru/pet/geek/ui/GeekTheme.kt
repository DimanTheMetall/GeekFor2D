package ru.pet.geek.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember


@Composable
fun GeekTheme(
    isDarkTheme: Boolean = false,
    content: @Composable () -> Unit
) {

    val themeHolder = remember(isDarkTheme) { ThemeHolderImpl(isDarkTheme) }

    val colors = remember(isDarkTheme) {
        themeHolder.geekPalette()
    }

    val images = remember(isDarkTheme) {
        themeHolder.geekImages()
    }

    val typography = remember {
        geekTypography()
    }

    CompositionLocalProvider(
        LocalGeekColors provides colors,
        LocalGeekImages provides images,
        LocalGeekTypography provides typography,
        content = {
            MaterialTheme(
                colorScheme = colorScheme,
                typography = Typography,
                content = content,
            )
        }
    )

}

object GeekTheme {
    val colors: GeekColors
        @[Composable ReadOnlyComposable]
        get() = LocalGeekColors.current

    val images: GeekImages
        @[Composable ReadOnlyComposable]
        get() = LocalGeekImages.current

    val typography
        @[Composable ReadOnlyComposable]
        get() = LocalGeekTypography.current
}