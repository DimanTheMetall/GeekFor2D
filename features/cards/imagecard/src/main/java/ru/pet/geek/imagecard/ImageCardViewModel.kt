package ru.pet.geek.imagecard

import androidx.lifecycle.ViewModel
import ru.pet.geek.imagecard.api.ImageCardNavApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.pet.geek.core.navigation.GeekRoute
import ru.pet.geek.imagecard.api.ImageCardDataApi

class ImageCardViewModel @AssistedInject constructor(
    private val imageCardNavApi: ImageCardNavApi,
    private val imageCardDataApi: ImageCardDataApi,
    @Assisted
    private val imageCardScreenRoute: GeekRoute.ImageRoute,
) : ViewModel() {

    private val mutableUiState = MutableStateFlow<ImageScreenState>(ImageScreenState.Loading)
    val uiState = mutableUiState.asStateFlow()


//    private fun initialiseState(): ImageScreenState {
//        return when (val configuration = imageCardScreenConfiguration) {
//            is ImageCardScreenConfiguration.ImageUrlScreenConfiguration -> ImageScreenState.Success()
//        }
//    }

    @AssistedFactory
    interface Factory {
        fun create(imageCardScreenRoute: GeekRoute.ImageRoute): ImageCardViewModel
    }

}