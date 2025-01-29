package ru.pet.geek.ui

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.pet.geek.utils.compositionProviderError

private val TTTFamily = FontFamily(
    Font(R.font.tttbold, FontWeight.Bold),
    Font(R.font.tttregular, FontWeight.Normal)
)

private fun geekSmall(
    fontFamily: FontFamily,
    fontWeight: FontWeight
): TextStyle = TextStyle(
    fontFamily = fontFamily,
    fontWeight = fontWeight,
    fontSize = 14.sp,
    lineHeight = 20.sp
)

private fun geekMedium(
    fontFamily: FontFamily,
    fontWeight: FontWeight
): TextStyle = TextStyle(
    fontFamily = fontFamily,
    fontWeight = fontWeight,
    fontSize = 24.sp,
    lineHeight = 32.sp
)

private fun geekLarge(
    fontFamily: FontFamily,
    fontWeight: FontWeight
): TextStyle = TextStyle(
    fontFamily = fontFamily,
    fontWeight = fontWeight,
    fontSize = 36.sp,
    lineHeight = 40.sp
)


interface GeekTypography {
    val tttSmallRegular: TextStyle
    val tttMediumRegular: TextStyle
    val tttLargeRegular: TextStyle
    val tttSmallBold: TextStyle
    val tttMediumBold: TextStyle
    val tttLargeBold: TextStyle
}

internal class GeekTypographyImpl: GeekTypography {
    override val tttSmallRegular: TextStyle = geekSmall(fontFamily = TTTFamily, fontWeight = FontWeight.Normal)
    override val tttMediumRegular: TextStyle = geekMedium(fontFamily = TTTFamily, fontWeight = FontWeight.Normal)
    override val tttLargeRegular: TextStyle = geekLarge(fontFamily = TTTFamily, fontWeight = FontWeight.Normal)
    override val tttSmallBold: TextStyle = geekSmall(fontFamily = TTTFamily, fontWeight = FontWeight.Bold)
    override val tttMediumBold: TextStyle = geekMedium(fontFamily = TTTFamily, fontWeight = FontWeight.Bold)
    override val tttLargeBold: TextStyle = geekLarge(fontFamily = TTTFamily, fontWeight = FontWeight.Bold)
}


fun geekTypography(): GeekTypography {
    return GeekTypographyImpl()
}

val LocalGeekTypography = staticCompositionLocalOf<GeekTypography> {
    compositionProviderError<GeekTypography>()
}