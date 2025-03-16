package ru.pet.geek.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.pet.geek.modifierExt.shimmerEffect
import ru.pet.geek.ui.GeekTheme

@Composable
private fun TestContent() {
    Row(modifier = Modifier) {
        Box(Modifier.size(40.dp).shimmerEffect())
        Spacer(Modifier.width(10.dp))
        Box(Modifier.size(height = 40.dp, width = 100.dp).shimmerEffect())
    }
}

@[Composable Preview]
private fun ShimmerPreview() {
    GeekTheme(isDarkTheme = false) {
        TestContent()
    }
}
