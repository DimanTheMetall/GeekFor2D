package ru.pet.geek.navigationcontroller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.Flow
import ru.pet.geek.core.navigation.RootScreen
import ru.pet.geek.core.navigation.Screen

interface NavigationEvents {
    val actions: Flow<NavigationAction>
}

@Composable
fun ComposeNavigationController(
    api: NavigationEvents,
    navHostController: NavHostController = rememberNavController()
) {
    LaunchedEffect(Unit) {
        api.actions.collect { action ->
            when (action) {
                is NavigationAction.Back -> navHostController.back()
                is NavigationAction.OpenNext -> navHostController.openNext(action.screen)
                is NavigationAction.SelectStack -> navHostController.selectStack(action.root)
            }
        }
    }
}

internal fun NavHostController.openNext(screen: Screen) {
    this.navigate(screen)
}

internal fun NavHostController.back() {
    this.navigateUp()
}

internal fun NavHostController.selectStack(screen: RootScreen) {
    this.navigate(screen) {
        launchSingleTop = true
        restoreState = true
        popUpTo(route = graph.startDestinationRoute!!) {
            saveState = true
        }
    }
}