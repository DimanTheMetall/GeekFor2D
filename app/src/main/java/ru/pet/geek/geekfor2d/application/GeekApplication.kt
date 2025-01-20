package ru.pet.geek.geekfor2d.application

import android.app.Application
import ru.pet.geek.geekfor2d.di.DaggerAppComponent

class GeekApplication : Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
    }
}