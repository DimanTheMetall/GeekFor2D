package ru.pet.geek.geekfor2d.di.modules

import ru.pet.geek.imagecard.api.ImageCardNavApi
import dagger.Module
import dagger.Provides
import ru.pet.geek.navigationcontroller.NavigationControllerApi

@Module
class ImageCardModule {

    @Provides
    fun provideImageCardNavApi(navController: NavigationControllerApi): ImageCardNavApi = object : ImageCardNavApi {
        override fun goBack() = navController.back()
    }
}