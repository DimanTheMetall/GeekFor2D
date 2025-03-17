package ru.pet.geek.features.feed

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.serialization.Serializable
import ru.pet.geek.core.dagger.DaggerViewModel
import ru.pet.geek.core.screens.BaseScreen
import ru.pet.geek.features.feed.di.FeedComponentViewModel
import ru.pet.geek.features.feed.ui.RandomsWidget

@Serializable
class FeedScreen : BaseScreen() {
    @Composable
    override fun Content() {
        val component = viewModel<FeedComponentViewModel>().component
        val viewModel = DaggerViewModel { component.getViewModelFactory().create() }

        val state by viewModel.uiState.collectAsState()

        StateSelector(state)
    }
}

@Composable
private fun StateSelector(state: FeedUiState) {
    when (state) {
        is FeedUiState.Error -> Error()
        is FeedUiState.Loading -> Loading()
        is FeedUiState.Success -> Success(state)
    }
}

@Composable
private fun Loading() {
}

@Composable
private fun Error() {
}

@Composable
private fun Success(state: FeedUiState.Success) {
    LazyColumn(
        modifier =
            Modifier
                .padding(horizontal = 16.dp)
                .statusBarsPadding(),
    ) {
        items(items = state.uiInfo) { item ->
            ItemSelector(item)
        }
    }
}

@Composable
private fun ItemSelector(item: FeedItemUi) {
    when (item) {
        is FeedItemUi.RandomWidget ->
            RandomsWidget(
                modifier = Modifier.fillMaxWidth(),
                uiInfo = item.uiInfo,
            )
    }
}
