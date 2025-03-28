package ru.pet.geek.utils

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

interface TextResHolder : UiInterface {
    @get:StringRes
    val textRes: Int
}

@Composable
fun TextResHolder.text(): String = stringResource(textRes)
