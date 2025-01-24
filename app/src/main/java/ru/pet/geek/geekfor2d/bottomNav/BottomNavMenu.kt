package ru.pet.geek.geekfor2d.bottomNav

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.pet.geek.core.navigation.RootScreen


@Composable
internal fun BottomNavMenu(
    modifier: Modifier = Modifier,
    roots: Set<RootScreen>,
    onClickRoot: (RootScreen) -> Unit = {}
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Absolute.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        roots.forEach { root ->
            Image(
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = { onClickRoot.invoke(root) }),
                contentDescription = null,
                painter = painterResource(root.iconRes)
            )
        }
    }
}
