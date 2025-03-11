package ru.pet.geek.ui

interface ThemeHolder {
    val isDark: Boolean

    infix fun <T> T.darkVariant(darkVariant: T) = if (isDark) darkVariant else this
}

internal class ThemeHolderImpl(
    override val isDark: Boolean,
) : ThemeHolder
