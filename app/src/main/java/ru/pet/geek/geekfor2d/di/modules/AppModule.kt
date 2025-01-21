package ru.pet.geek.geekfor2d.di.modules

import dagger.Module
import dagger.Provides
import ru.pet.geek.navigationcontroller.NavigationController
import ru.pet.geek.navigationcontroller.NavigationControllerApi

@Module
class AppModule {

    @Provides
    fun provideNavigationControllerApi(): NavigationControllerApi {
        return NavigationController()
    }
}