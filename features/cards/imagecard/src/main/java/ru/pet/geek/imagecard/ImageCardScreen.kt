package ru.pet.geek.imagecard

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.pet.geek.core.dagger.DaggerViewModel
import ru.pet.geek.core.screens.BaseScreen
import ru.pet.geek.imagecard.di.ImageCardComponentViewModel

class ImageCardScreen(
    private val screenConfiguration: ImageCardScreenConfiguration
): BaseScreen() {

    @Composable
    override fun Content() {
        val component = viewModel<ImageCardComponentViewModel>().component
        val vm = DaggerViewModel { component.getViewModelFactory().create(imageCardScreenConfiguration = screenConfiguration) }

        
    }
}
