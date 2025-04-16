package com.example.manga

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cards.basecards.LeftRightButtonsWidget
import com.example.cards.basecards.StateSelector
import com.example.manga.di.MangaRandomCardComponentViewModel
import kotlinx.serialization.Serializable
import ru.pet.geek.core.dagger.DaggerViewModel
import ru.pet.geek.core.screens.BaseScreen
import ru.pet.geek.widgets.bottomNavMenuPadding

@Serializable
class MangaRandomCardScreen : BaseScreen() {
    @Composable
    override fun Content() {
        val component = viewModel<MangaRandomCardComponentViewModel>().component
        val vm = DaggerViewModel { component.getViewModelFactory().create() }
        val uiState by vm.uiState.collectAsState()
        val buttonsUiState by vm.buttonsUiState.collectAsState()
        val recState by vm.recommendationsUIState.collectAsState()

        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .systemBarsPadding(),
        ) {
            Box(modifier = Modifier.fillMaxSize().bottomNavMenuPadding()) {
                StateSelector(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .align(Alignment.TopCenter),
                    state = uiState,
                    recommendations = recState,
                )
                LeftRightButtonsWidget(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                            .align(Alignment.BottomCenter),
                    uiInfo = buttonsUiState,
                )
            }
        }
    }

    override fun equals(other: Any?): Boolean =
        when {
            other is MangaRandomCardScreen -> true
            else -> false
        }
}
