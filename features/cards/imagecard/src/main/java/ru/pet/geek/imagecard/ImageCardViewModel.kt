package ru.pet.geek.imagecard

import androidx.lifecycle.ViewModel
import ru.pet.geek.imagecard.api.ImageCardNavApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ImageCardViewModel @AssistedInject constructor(
    private val imageCardNavApi: ImageCardNavApi,
    @Assisted
    private val imageCardScreenConfiguration: ImageCardScreenConfiguration,
) : ViewModel() {

    private val mutableUiState = MutableStateFlow<ImageScreenState>(initialiseState())
    val uiState = mutableUiState.asStateFlow()


    private fun initialiseState(): ImageScreenState {
        return when (val configuration = imageCardScreenConfiguration) {
            is ImageCardScreenConfiguration.ImageUrlScreenConfiguration -> ImageScreenState.ImageFromUrl(
                imageUrl = configuration.imageUrl
            )
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(imageCardScreenConfiguration: ImageCardScreenConfiguration): ImageCardViewModel
    }

}