package ru.pet.geek.geekfor2d.di.dependency

import android.content.Context
import ru.pet.geek.core.utils.dependencyProviderError
import ru.pet.geek.features.feed.di.FeedDependenciesProvider

interface DependencyProvider: AppDependencyProvider, FeedDependenciesProvider

interface AppDependencyProvider {
    val dependency: AppDependency
}

fun Context.getAppDependency(): AppDependency {
    return (applicationContext as? AppDependencyProvider)?.dependency?: dependencyProviderError()
}