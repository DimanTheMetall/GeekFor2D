package ru.pet.geek.features.feed.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import dagger.Component
import ru.pet.geek.core.utils.dependencyProviderError
import ru.pet.geek.features.feed.FeedViewModel

@Component(
    dependencies = [FeedDependency::class],
)
internal interface FeedComponent {
    fun getViewModelFactory(): FeedViewModel.Factory

    @Component.Factory
    interface Factory {
        fun create(feedDependency: FeedDependency): FeedComponent
    }
}

internal class FeedComponentViewModel(
    application: Application,
) : AndroidViewModel(application) {
    val component: FeedComponent =
        DaggerFeedComponent.factory().create(application.getDependencies())
}

interface FeedDependenciesProvider {
    val dependency: FeedDependency
}

private fun Context.getDependencies() =
    (applicationContext as? FeedDependenciesProvider)?.dependency
        ?: dependencyProviderError("FeedDependency")
