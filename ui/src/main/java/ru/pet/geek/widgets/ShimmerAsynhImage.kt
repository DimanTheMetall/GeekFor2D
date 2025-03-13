package ru.pet.geek.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter.State
import ru.pet.geek.modifierExt.shimmerEffect

@Composable
fun ShimmerAsynhImage(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    model: Any?,
) {
    val imageState = remember { mutableStateOf<State>(State.Loading(null)) }
    val onStateChanged: ((State) -> Unit) = remember { { state -> imageState.value = state } }
    AsyncImage(
        modifier = modifier.shimmerEffect(isEnabled = imageState.value is State.Loading),
        model = model,
        contentDescription = contentDescription,
        onState = onStateChanged,
    )
}
