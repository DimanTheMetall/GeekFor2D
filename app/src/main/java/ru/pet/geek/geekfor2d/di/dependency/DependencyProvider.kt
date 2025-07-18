package ru.pet.geek.geekfor2d.di.dependency

import android.content.Context
import ru.pet.geek.core.utils.dependencyProviderError
import ru.pet.geek.features.feed.di.FeedDependenciesProvider
import ru.pet.geek.imagecard.di.ImageCardDependenciesProvider
import ru.pet.geek.manga.di.MangaRandomCardDependencyProvider

interface DependencyProvider :
    AppDependencyProvider,
    FeedDependenciesProvider,
    MangaRandomCardDependencyProvider,
    ImageCardDependenciesProvider

interface AppDependencyProvider {
    val dependency: AppDependency
}

fun Context.getAppDependency(): AppDependency =
    (applicationContext as? AppDependencyProvider)?.dependency ?: dependencyProviderError()
