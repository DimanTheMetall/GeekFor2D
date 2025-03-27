package com.example.cards.basecards

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.pet.geek.widgets.MainInfoWidgetLoading

@Composable
fun LoadingCardState(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        MainInfoWidgetLoading()
    }
}
