package com.example.manga

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cards.basecards.StateSelector
import com.example.manga.di.MangaRandomCardComponentViewModel
import kotlinx.serialization.Serializable
import ru.pet.geek.core.screens.BaseScreen

@Serializable
class MangaRandomCardScreen : BaseScreen() {

    @Composable
    override fun Content() {
        val component = viewModel<MangaRandomCardComponentViewModel>().component
        val vm = component.getViewModelFactory().create()
        val uiState by vm.uiState.collectAsState()

        StateSelector(state = uiState)
    }

    override fun hashCode(): Int = super.hashCode()

    override fun equals(other: Any?): Boolean =
        when {
            other is MangaRandomCardScreen -> true
            else -> false
        }
}
