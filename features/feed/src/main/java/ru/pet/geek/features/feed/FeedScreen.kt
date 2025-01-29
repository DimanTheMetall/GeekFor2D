package ru.pet.geek.features.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.serialization.Serializable
import ru.pet.geek.core.screens.BaseRootScreen
import ru.pet.geek.features.feed.di.FeedComponentViewModel
import ru.pet.geek.feed.R

@Serializable
class FeedScreen : BaseRootScreen() {

    override val iconRes: Int
        get() = R.drawable.feed_root_ic

    @Composable
    override fun Content() {
        val component = viewModel<FeedComponentViewModel>().component
        val viewModel = component.getViewModelFactory().create()


        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Red))
    }

}
