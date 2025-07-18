package ru.pet.geek.navigationcontroller

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.pet.geek.core.LogConstants


class LoggerNavController(private val navigationControllerApi: NavigationControllerApi) :
    NavigationControllerApi by navigationControllerApi {

    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        scope.launch {
            navigationControllerApi.getEventFlow()
                .collect { navAction ->
                    when (navAction) {
                        is NavigationAction.Back -> {
                            Log.i(
                                LogConstants.NAVIGATION_TAG,
                                "Received ${navAction::class.simpleName} action"
                            )
                        }

                        is NavigationAction.OpenNext -> {
                            Log.i(
                                LogConstants.NAVIGATION_TAG,
                                "Received ${navAction::class.simpleName} action to open " +
                                        "screen ${navAction.screen::class.simpleName}"
                            )
                        }

                        is NavigationAction.SelectStack -> {
                            Log.i(
                                LogConstants.NAVIGATION_TAG,
                                "Received ${navAction::class.simpleName} action to open stack " +
                                        "${navAction.root::class.simpleName}"
                            )
                        }
                    }
                }
        }
    }
}