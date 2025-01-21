package ru.pet.geek.core.dagger

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.pet.geek.core.utils.viewModelStoreProvidedError

@[Composable Suppress("UNCHECKED_CAST")]
inline fun <reified T : ViewModel> DaggerViewModel(
    key: String? = null,
    viewModelStoreOwner: ViewModelStoreOwner = LocalViewModelStoreOwner.current
        ?: viewModelStoreProvidedError(),
    crossinline instanceCreator: () -> T
): T {
    return viewModel(
        modelClass = T::class.java,
        key = key,
        viewModelStoreOwner = viewModelStoreOwner,
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return instanceCreator.invoke() as T
            }
        }
    )
}