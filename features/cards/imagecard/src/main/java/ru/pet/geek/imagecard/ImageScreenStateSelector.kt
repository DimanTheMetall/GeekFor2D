package ru.pet.geek.imagecard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.pet.geek.widgets.BaseErrorScreenWidget

@Composable
internal fun ImageScreenStateSelector(
    state: ImageScreenState
) {
    when (state) {
        is ImageScreenState.Error -> {
            BaseErrorScreenWidget(
                modifier = Modifier.fillMaxSize(),
                e = state.e,
                refreshButton = state.refreshButton,
            )
        }

        is ImageScreenState.Loading -> Unit
        is ImageScreenState.Success -> Unit
    }
}

