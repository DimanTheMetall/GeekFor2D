package ru.pet.geek.geekfor2d.di

import dagger.BindsInstance
import dagger.Component
import ru.pet.geek.geekfor2d.application.GeekApplication
import ru.pet.geek.geekfor2d.di.dependency.AppDependency
import ru.pet.geek.geekfor2d.di.modules.AppModule
import ru.pet.geek.geekfor2d.di.modules.FeedModule
import ru.pet.geek.geekfor2d.di.modules.RemoteSourceModule
import ru.pet.geek.geekfor2d.di.modules.RepositoryModule
import javax.inject.Scope

@AppScope
@Component(
    modules = [
        AppModule::class,
        RepositoryModule::class,
        RemoteSourceModule::class,
        FeedModule::class,
    ]
)
interface AppComponent : AppDependency {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: GeekApplication): AppComponent
    }
}

@Scope
annotation class AppScope