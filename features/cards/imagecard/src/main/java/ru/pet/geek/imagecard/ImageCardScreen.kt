package ru.pet.geek.imagecard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.serialization.Serializable
import ru.pet.geek.core.dagger.DaggerViewModel
import ru.pet.geek.core.navigation.GeekRoute
import ru.pet.geek.core.screens.BaseScreen
import ru.pet.geek.imagecard.di.ImageCardComponentViewModel

@Serializable
data class ImageCardScreen(
    private val screenConfiguration: GeekRoute.ImageRoute,
): BaseScreen() {

    @Composable
    override fun Content() {
        val component = viewModel<ImageCardComponentViewModel>().component
        val vm = DaggerViewModel { component.getViewModelFactory().create(imageCardScreenRoute = screenConfiguration) }

        val uiState by vm.uiState.collectAsState()


        ImageScreenStateSelector(
            state = uiState,
        )
    }
}
