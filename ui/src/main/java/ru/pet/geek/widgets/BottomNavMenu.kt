package ru.pet.geek.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.pet.geek.api.Clickable
import ru.pet.geek.api.IcResHolderUi
import ru.pet.geek.modifierExt.noRippleClickable

data class BottomNavItem(
    override val icRes: Int,
    override val onClick: () -> Unit
): IcResHolderUi, Clickable

@Composable
fun BottomNavMenu(
    modifier: Modifier = Modifier,
    roots: List<BottomNavItem>,
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
                    .noRippleClickable(clickable = root),
                contentDescription = null,
                painter = painterResource(root.icRes)
            )
        }
    }
}
