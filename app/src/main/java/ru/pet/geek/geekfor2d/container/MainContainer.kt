package ru.pet.geek.geekfor2d.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import ru.pet.geek.core.dagger.DaggerViewModel
import ru.pet.geek.core.screens.BaseScreen
import ru.pet.geek.geekfor2d.MainActivity
import ru.pet.geek.geekfor2d.di.dependency.getAppDependency
import ru.pet.geek.navigationcontroller.ComposeNavigationController
import ru.pet.geek.ui.GeekTheme
import ru.pet.geek.utils.context
import ru.pet.geek.widgets.BottomNavMenu

private val bottomMenuGradient
    @Composable
    get() =
        Brush.horizontalGradient(
            colors = listOf(GeekTheme.colors.pinkLight, GeekTheme.colors.blueLight),
            tileMode = TileMode.Clamp
        )

@Serializable
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

        ComposeNavigationController(api = viewModel, navHostController = navController)

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = FeedContainer::class
            ) {
                addRootsScreen()
            }

            BottomNavMenu(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(34.dp)
                    .background(bottomMenuGradient)
                    .align(Alignment.BottomCenter),
                roots = viewModel.roots,
            )
        }
    }


}




