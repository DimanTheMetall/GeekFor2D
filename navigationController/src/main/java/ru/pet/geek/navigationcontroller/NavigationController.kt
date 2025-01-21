package ru.pet.geek.navigationcontroller

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ru.pet.geek.core.navigation.RootScreen
import ru.pet.geek.core.navigation.Screen

interface NavigationControllerApi {

    fun getEventFlow(): Flow<NavigationAction>

    fun openNext(screen: Screen)

    fun back()

    fun selectStack(root: RootScreen)

}

class NavigationController : NavigationControllerApi {
    private val scope = CoroutineScope(Dispatchers.Main)

    private val mutableSharedFlow = MutableSharedFlow<NavigationAction>()

    override fun getEventFlow(): Flow<NavigationAction> = mutableSharedFlow

    override fun openNext(screen: Screen) {
        require(screen !is RootScreen) {
            "Use non ${RootScreen::name} to open next screen"
        }
        scope.launch {
            mutableSharedFlow.emit(NavigationAction.OpenNext(screen = screen))
        }
    }

    override fun back() {
        scope.launch {
            mutableSharedFlow.emit(NavigationAction.Back)
        }
    }

    override fun selectStack(root: RootScreen) {
        scope.launch {
            mutableSharedFlow.emit(NavigationAction.SelectStack(root = root))
        }
    }

}