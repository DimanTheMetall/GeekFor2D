package ru.pet.geek.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable
import ru.pet.geek.core.screens.BaseScreen

@Serializable
class FavoriteScreen : BaseScreen() {

    @Composable
    override fun Content() {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue))
    }

}
