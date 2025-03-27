package ru.pet.geek.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.pet.geek.ui.GeekTheme

@Composable
fun PreviewBox(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    GeekTheme {
        Box(
            modifier =
                modifier
                    .background(GeekTheme.colors.backgroundPrimary)
                    .padding(10.dp),
            content = content,
        )
    }
}

@Composable
fun PreviewColumn(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    GeekTheme {
        Column(
            modifier =
                modifier
                    .background(GeekTheme.colors.backgroundPrimary)
                    .padding(10.dp),
            content = content,
        )
    }
}

@Composable
fun PreviewRow(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    GeekTheme {
        Row(
            modifier =
                modifier
                    .background(GeekTheme.colors.backgroundPrimary)
                    .padding(10.dp),
            content = content,
        )
    }
}
