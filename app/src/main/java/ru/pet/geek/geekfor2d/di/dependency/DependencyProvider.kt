package ru.pet.geek.geekfor2d.di.dependency

import android.content.Context
import com.example.manga.di.MangaRandomCardDependencyProvider
import ru.pet.geek.core.utils.dependencyProviderError
import ru.pet.geek.features.feed.di.FeedDependenciesProvider

interface DependencyProvider :
    AppDependencyProvider,
    FeedDependenciesProvider,
    MangaRandomCardDependencyProvider

interface AppDependencyProvider {
    val dependency: AppDependency
}

fun Context.getAppDependency(): AppDependency = (applicationContext as? AppDependencyProvider)?.dependency ?: dependencyProviderError()
