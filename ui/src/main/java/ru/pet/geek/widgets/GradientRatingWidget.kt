package ru.pet.geek.widgets

import androidx.annotation.FloatRange
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.pet.geek.ui.GeekTheme
import ru.pet.geek.ui.R

private const val CORNET_SIZE = 16f

@Composable
fun GradientRatingWidget(
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.0, to = 10.0) rating: Float,
    ratesClick: Int,
    fromColor: Color,
    toColor: Color,
) {
    Row(
        modifier =
            modifier
                .clip(RoundedCornerShape(CORNET_SIZE))
                .drawBehind {
                    val mainProgressWidth = size.width * (rating / 10)
                    val gradientWidth = (size.width - mainProgressWidth).coerceAtMost(size.width / 10)
                    val height = size.height
                    val gradient =
                        Brush.horizontalGradient(
                            colors = listOf(fromColor, toColor),
                            startX = mainProgressWidth,
                            endX = mainProgressWidth + gradientWidth,
                        )
                    drawRoundRect(color = fromColor, size = Size(mainProgressWidth, height = height))
                    drawRoundRect(
                        topLeft = Offset(x = mainProgressWidth, y = 0f),
                        brush = gradient,
                        size =
                            Size(
                                width = gradientWidth,
                                height = height,
                            ),
                    )
                }.padding(horizontal = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.size(14.dp),
                painter = painterResource(R.drawable.ui_ic_person),
                contentDescription = null,
            )
            Text(
                text = ratesClick.toString(),
                color = GeekTheme.colors.textPrimary,
                style = GeekTheme.typography.tttSmallRegular,
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = rating.toString(),
                color = GeekTheme.colors.textPrimary,
                style = GeekTheme.typography.tttSmallRegular,
            )
            Image(
                modifier = Modifier.size(14.dp),
                painter = painterResource(R.drawable.ui_ic_start),
                contentDescription = null,
            )
        }
    }
}

@[Composable Preview]
private fun GradientRatingPreview() {
    GeekTheme {
        Column(
            modifier = Modifier.width(200.dp).background(GeekTheme.colors.backgroundPrimary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            repeat(11) { int ->
                GradientRatingWidget(
                    modifier = Modifier.size(width = 150.dp, height = 20.dp),
                    rating = int.toFloat(),
                    fromColor = GeekTheme.colors.pinkLight,
                    toColor = GeekTheme.colors.transparent,
                    ratesClick = 3200,
                )
            }
        }
    }
}
