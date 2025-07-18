package ru.pet.geek.imagecard.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import ru.pet.geek.imagecard.ImageCardViewModel
import dagger.Component
import ru.pet.geek.core.utils.dependencyProviderError


@Component(
    dependencies = [ImageCardDependencies::class]
)
interface ImageCardComponent {
    fun getViewModelFactory(): ImageCardViewModel.Factory

    @Component.Factory
    interface Factory {
        fun create(dependencies: ImageCardDependencies): ImageCardComponent
    }
}

interface ImageCardDependenciesProvider {
    val dependency: ImageCardDependencies
}

internal class ImageCardComponentViewModel(application: Application) : AndroidViewModel(application) {
    val component = DaggerImageCardComponent.factory().create(application.getDependencies())
}

private fun Context.getDependencies(): ImageCardDependencies =
    (this as? ImageCardDependenciesProvider)?.dependency
        ?: dependencyProviderError("MangaRandomCardDependencies")