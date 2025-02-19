package ru.pet.geek.utils

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable

@Composable
fun PagerState.currentPageOffset(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}