package ru.pet.geek

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.pet.geek.utils.UiInterface

interface UiText : UiInterface {
    @Composable
    fun toValue(): String

    @JvmInline
    value class StringText(
        val value: String,
    ) : UiText {
        @Composable
        override fun toValue(): String = value
    }

    @JvmInline
    value class ResourcesText(
        @StringRes val res: Int,
    ) : UiText {
        @Composable
        override fun toValue(): String = stringResource(res)
    }
}
