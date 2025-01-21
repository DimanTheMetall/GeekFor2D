package ru.pet.geek.geekfor2d.container

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import ru.pet.geek.core.dagger.DaggerViewModel
import ru.pet.geek.core.screens.BaseScreen
import ru.pet.geek.geekfor2d.MainActivity
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

        ComposeNavigationController(api = viewModel)

    }

}