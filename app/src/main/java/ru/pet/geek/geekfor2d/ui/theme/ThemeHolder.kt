package ru.pet.geek.geekfor2d.ui.theme

import androidx.compose.ui.graphics.Color

interface ThemeHolder {
    val isDark: Boolean

    infix fun Any.darkVariant(darkVariant: Color) = if (isDark) darkVariant else this
}

internal class ThemeHolderImpl(override val isDark: Boolean) : ThemeHolder