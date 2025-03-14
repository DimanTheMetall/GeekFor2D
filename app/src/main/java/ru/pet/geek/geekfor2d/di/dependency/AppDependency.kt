package ru.pet.geek.geekfor2d.di.dependency

import ru.pet.geek.geekfor2d.container.MainViewModel

interface AppDependency : FeatureDependency {
    fun getMainViewModelFactory(): MainViewModel.Factory
}
