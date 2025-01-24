package ru.pet.geek.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable
import ru.pet.geek.core.screens.BaseRootScreen

@Serializable
class FavoriteScreen : BaseRootScreen() {
    override val iconRes: Int
        get() = R.drawable.favorite_root_ic

    @Composable
    override fun Content() {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue))
    }

}
