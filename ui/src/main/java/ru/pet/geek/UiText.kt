package ru.pet.geek

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.pet.geek.utils.UiInterface


interface UiText : UiInterface {

    @Composable
    fun toValue(): String

    data class StringText(val value: String) : UiText {
        @Composable
        override fun toValue(): String {
            return value
        }
    }


    data class ResourcesText(@StringRes val res: Int) : UiText {
        @Composable
        override fun toValue(): String {
            return stringResource(res)
        }
    }
}