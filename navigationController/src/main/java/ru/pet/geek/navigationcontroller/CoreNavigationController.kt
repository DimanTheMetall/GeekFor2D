package ru.pet.geek.navigationcontroller

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ru.pet.geek.core.navigation.GeekRoute

interface NavigationControllerApi {

    fun getEventFlow(): Flow<NavigationAction>

    fun openNext(route: GeekRoute)

    fun back()

    fun selectStack(root: GeekRoute.ContainerRoute)

}

class CoreNavigationController : NavigationControllerApi {
    private val scope = CoroutineScope(Dispatchers.Main)

    private val mutableSharedFlow = MutableSharedFlow<NavigationAction>()

    override fun getEventFlow(): Flow<NavigationAction> = mutableSharedFlow

    override fun openNext(route: GeekRoute) {
        require(route !is GeekRoute.ContainerRoute) {
            "Use non ${GeekRoute.ContainerRoute::class} to open next screen"
        }
        scope.launch {
            mutableSharedFlow.emit(NavigationAction.OpenNext(route = route))
        }
    }

    override fun back() {
        scope.launch {
            mutableSharedFlow.emit(NavigationAction.Back)
        }
    }

    override fun selectStack(root: GeekRoute.ContainerRoute) {
        scope.launch {
            mutableSharedFlow.emit(NavigationAction.SelectStack(route = root))
        }
    }

}