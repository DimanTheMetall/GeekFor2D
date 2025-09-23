package ru.pet.geek.geekfor2d.container

import androidx.lifecycle.ViewModel
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import ru.pet.geek.core.navigation.GeekRoute
import ru.pet.geek.geekfor2d.R
import ru.pet.geek.navigationcontroller.NavigationAction
import ru.pet.geek.navigationcontroller.NavigationControllerApi
import ru.pet.geek.navigationcontroller.NavigationEvents
import ru.pet.geek.widgets.BottomNavItem

class MainViewModel @AssistedInject constructor(
    private val navControllerApi: NavigationControllerApi,
) : ViewModel(), NavigationEvents {
    @AssistedFactory
    interface Factory {
        fun create(): MainViewModel
    }

    val roots =
        buildList {
            add(
                BottomNavItem(
                    icRes = R.drawable.app_ic_feed,
                    onClick = {
                        selectStack(GeekRoute.ContainerRoute.FeedContainer)
                    },
                ),
            )

            add(
                BottomNavItem(
                    icRes = R.drawable.app_ic_favorite,
                    onClick = {
                        selectStack(GeekRoute.ContainerRoute.FavoriteContainer)
                    },
                ),
            )
        }

    private fun selectStack(root: GeekRoute.ContainerRoute) = navControllerApi.selectStack(root)

    override val actions: Flow<NavigationAction> = navControllerApi.getEventFlow()
}
