package ru.pet.geek.geekfor2d.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.pet.geek.core.utils.FileUtils

@Module
class UtilsModule {

    @Provides
    fun provideFileUtils(context: Context): FileUtils = FileUtils(context = context)
}