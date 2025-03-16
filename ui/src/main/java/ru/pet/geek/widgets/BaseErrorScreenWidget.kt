package ru.pet.geek.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.pet.geek.ui.GeekTheme
import ru.pet.geek.ui.R
import ru.pet.geek.utils.PreviewBox
import ru.pet.geek.utils.SpacerHeight

@Composable
fun BaseErrorScreenWidget(
    modifier: Modifier = Modifier,
    e: Throwable,
    refreshButton: CircleButtonInfo,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(0.6f),
            contentDescription = null,
            painter = painterResource(R.drawable.ui_sorry_girl),
        )
        SpacerHeight(8.dp)
        Text(
            modifier = Modifier,
            text = e.toErrorText(),
            color = GeekTheme.colors.textPrimary,
            style = GeekTheme.typography.tttSmallRegular,
            textAlign = TextAlign.Center,
        )
        SpacerHeight(8.dp)
        CircleIconButton(
            uiInfo = refreshButton,
        )
    }
}

@Composable
private fun Throwable.toErrorText(): String = stringResource(R.string.ui_error_common_text)

@[Composable Preview]
private fun BaseErrorScreenWidgetPreview() {
    PreviewBox {
        BaseErrorScreenWidget(
            modifier = Modifier.fillMaxSize(),
            e = RuntimeException(),
            refreshButton = CircleStaticLoadingButton(onClick = {}),
        )
    }
}
