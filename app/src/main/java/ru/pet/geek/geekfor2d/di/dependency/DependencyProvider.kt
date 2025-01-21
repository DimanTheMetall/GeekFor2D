package ru.pet.geek.geekfor2d.di.dependency

import android.content.Context
import ru.pet.geek.core.utils.dependencyProviderError

interface DependencyProvider: AppDependencyProvider

interface AppDependencyProvider {
    val dependency: AppDependency
}

fun Context.getAppDependency(): AppDependency {
    return (applicationContext as? AppDependencyProvider)?.dependency?: dependencyProviderError()
}