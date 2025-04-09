package com.example.cards.basecards

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.pet.geek.utils.SpacerHeight
import ru.pet.geek.widgets.BadgeListWidgetLoading
import ru.pet.geek.widgets.MainInfoWidgetLoading
import ru.pet.geek.widgets.SliderContentWidgetLoading

@Composable
fun LoadingCardState(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        MainInfoWidgetLoading()
        SpacerHeight(10.dp)
        SliderContentWidgetLoading(height = 100.dp)
        SpacerHeight(10.dp)
        BadgeListWidgetLoading()
    }
}
