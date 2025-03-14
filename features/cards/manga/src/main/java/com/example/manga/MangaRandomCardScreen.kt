package com.example.manga

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.manga.di.MangaRandomCardComponentViewModel
import ru.pet.geek.core.screens.BaseScreen

class MangaRandomCardScreen : BaseScreen() {
    @Composable
    override fun Content() {
        val component = viewModel<MangaRandomCardComponentViewModel>().component
        val vm = component.getViewModelFactory().create()


    }
}
