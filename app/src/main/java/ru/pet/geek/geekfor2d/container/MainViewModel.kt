package ru.pet.geek.geekfor2d.container

import androidx.lifecycle.ViewModel
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import ru.pet.geek.navigationcontroller.NavigationAction
import ru.pet.geek.navigationcontroller.NavigationControllerApi
import ru.pet.geek.navigationcontroller.NavigationEvents

class MainViewModel @AssistedInject constructor(
    navControllerApi: NavigationControllerApi
): ViewModel(), NavigationEvents {


    @AssistedFactory
    interface Factory {
        fun create(): MainViewModel
    }

    override val actions: Flow<NavigationAction> = navControllerApi.getEventFlow()
}