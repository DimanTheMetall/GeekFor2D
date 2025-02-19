package ru.pet.geek.ui

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import ru.pet.geek.utils.compositionProviderError

fun ThemeHolder.geekPalette(): GeekColors {
    return GeekColorsImpl(
        violetLight = Color(0xFF8415FF) darkVariant Color(0xFF6D11D5),
        violetMedium = Color(0xFF5C11B0) darkVariant Color(0xFF4B0D91),
        violetDark = Color(0xFF310A6B) darkVariant Color(0xFF310A6B),
        orangeMedium = Color(0xFFB93C14) darkVariant Color(0xFF832D0F),
        blueMedium = Color(0xFF2F5AAD) darkVariant Color(0xFF27498F),
        textPrimary = Color(0xFF000000) darkVariant Color(0xFFFFFFFF),
        backgroundPrimary = Color(0xFFFFFFFF) darkVariant Color(0xFF2C2C2C),
        backgroundModal = Color(0xFFE7E7E7) darkVariant Color(0xFF383838),
        isDark = isDark,
    )
}

interface GeekColors {
    val greyscale0: Color
    val greyscale100: Color
    val greyscale200: Color
    val greyscale300: Color
    val greyscale400: Color
    val greyscale500: Color
    val greyscale600: Color
    val greyscale700: Color
    val greyscale800: Color
    val greyscale900: Color

    //violet
    val violetLight: Color
    val violetMedium: Color
    val violetDark: Color

    //background
    val backgroundPrimary: Color
    val backgroundModal: Color

    //text
    val textPrimary: Color

    //
    val orangeMedium: Color

    //
    val blueMedium: Color

    //
    val isDark: Boolean
}


internal class GeekColorsImpl(
    override val violetLight: Color,
    override val violetMedium: Color,
    override val violetDark: Color,
    override val orangeMedium: Color,
    override val blueMedium: Color,
    override val textPrimary: Color,
    override val backgroundPrimary: Color,
    override val backgroundModal: Color,
    override val isDark: Boolean,
) : GeekColors {
    //No theme extends
    override val greyscale0: Color = Color(0xFFFFFFFF)
    override val greyscale100: Color = Color(0xFFF2F3F7)
    override val greyscale200: Color = Color(0xFFE2E5EB)
    override val greyscale300: Color = Color(0xFFBBC1C7)
    override val greyscale400: Color = Color(0xFF969FA8)
    override val greyscale500: Color = Color(0xFF626C77)
    override val greyscale600: Color = Color(0xFF434A51)
    override val greyscale700: Color = Color(0xFF2C3135)
    override val greyscale800: Color = Color(0xFF1D2023)
    override val greyscale900: Color = Color(0xFF000000)
}

val LocalGeekColors = staticCompositionLocalOf<GeekColors> {
    compositionProviderError<GeekColors>()
}