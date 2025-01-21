package ru.pet.geek.geekfor2d.application

import android.app.Application
import ru.pet.geek.geekfor2d.di.DaggerAppComponent
import ru.pet.geek.geekfor2d.di.dependency.AppDependency
import ru.pet.geek.geekfor2d.di.dependency.DependencyProvider

class GeekApplication : Application(), DependencyProvider {

    private val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override val dependency: AppDependency
        get() = appComponent

    override fun onCreate() {
        super.onCreate()
    }


}