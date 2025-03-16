package com.example.manga.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.manga.MangaRandomCardViewModel
import dagger.Component
import ru.pet.geek.core.utils.dependencyProviderError

@Component(
    dependencies = [MangaRandomCardDependencies::class],
)
interface MangaRandomCardComponent {
    fun getViewModelFactory(): MangaRandomCardViewModel.Factory

    @Component.Factory
    interface Factory {
        fun create(dependency: MangaRandomCardDependencies): MangaRandomCardComponent
    }
}

interface MangaRandomCardDependencyProvider {
    val dependency: MangaRandomCardDependencies
}

internal class MangaRandomCardComponentViewModel(
    application: Application,
) : AndroidViewModel(application) {
    val component = DaggerMangaRandomCardComponent.factory().create(application.getDependency())
}

private fun Context.getDependency(): MangaRandomCardDependencies =
    (this as? MangaRandomCardDependencyProvider)?.dependency ?: dependencyProviderError("MangaRandomCardDependencies")
