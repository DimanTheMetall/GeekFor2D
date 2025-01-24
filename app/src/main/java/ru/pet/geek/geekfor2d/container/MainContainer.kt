package ru.pet.geek.geekfor2d.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import ru.pet.geek.core.dagger.DaggerViewModel
import ru.pet.geek.core.navigation.RootScreen

import ru.pet.geek.core.navigation.navKey
import ru.pet.geek.core.screens.BaseScreen
import ru.pet.geek.geekfor2d.MainActivity
import ru.pet.geek.geekfor2d.bottomNav.BottomNavMenu
import ru.pet.geek.geekfor2d.di.dependency.getAppDependency
import ru.pet.geek.navigationcontroller.ComposeNavigationController
import ru.pet.geek.utils.context

class MainContainer : BaseScreen() {

    @Composable
    override fun Content() {
        val context = context as MainActivity

        val component = remember {
            context.getAppDependency()
        }

        val viewModel = DaggerViewModel {
            component.getMainViewModelFactory().create()
        }


        val navController = rememberNavController()

        val roots = viewModel.roots

        ComposeNavigationController(api = viewModel, navHostController = navController)

        Box(modifier = Modifier.fillMaxSize()) {
            key(roots) {
                NavHost(
                    navController = navController,
                    startDestination = this@MainContainer::class.navKey()
                ) {
                    addRootsScreen(roots)
                }
            }

            BottomNavMenu(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(Color.Yellow)
                    .align(Alignment.BottomCenter),
                roots = roots,
                onClickRoot = viewModel::selectRoot
            )
        }
    }

}


private fun NavGraphBuilder.addRootsScreen(
    routs: Set<RootScreen>
) {
    navigation(
        route = MainContainer::class.navKey(), startDestination = routs.first()::class.navKey(),
    ) {

        routs.forEach { root ->
            composable(route = root::class.navKey()) {
                root.Content()
            }
        }

    }
}
