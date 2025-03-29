package ru.pet.geek.utils

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun PagerState.currentPageOffset(page: Int): Float = (currentPage - page) + currentPageOffsetFraction

@Composable
fun Dp.dpToPx(): Float = with(LocalDensity.current) { this@dpToPx.toPx() }

@Composable
fun Int.pxToDp() = with(LocalDensity.current) { this@pxToDp.toDp() }

@Composable
fun Float.pxToDp() = with(LocalDensity.current) { this@pxToDp.toDp() }
