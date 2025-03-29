package com.example.cards.basecards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.pet.geek.core.GeneralState
import ru.pet.geek.core.LocalResponse
import ru.pet.geek.core.mappers.toGeneralState
import ru.pet.geek.widgets.CircleStaticLoadingButton

abstract class BaseCardViewModel<T> : ViewModel() {
    protected abstract fun T.toUi(): SuccessUiState

    protected abstract suspend fun getData(): LocalResponse<T>

    protected val currentDataState = MutableStateFlow<GeneralState<T>>(GeneralState.Loading)

    protected val mutableUiState = MutableStateFlow<RandomCardUiState>(RandomCardUiState.Loading)
    val uiState = mutableUiState.asStateFlow()

    open fun onInit() {
        viewModelScope.launch {
            currentDataState
                .map { dataState -> dataState.toUiState() }
                .collect { uiState ->
                    mutableUiState.emit(uiState)
                }
        }
    }

    protected open fun loadData() {
        viewModelScope.launch {
            currentDataState.emit(GeneralState.Loading)
            currentDataState.emit(getData().toGeneralState())
        }
    }

    protected fun onRefreshClick() = refresh()

    protected fun refresh() {
        loadData()
    }

    protected fun GeneralState<T>.toUiState(): RandomCardUiState =
        when (this) {
            is GeneralState.Error ->
                RandomCardUiState.Error(
                    e = e,
                    uiInfo = CircleStaticLoadingButton(onClick = ::onRefreshClick),
                )

            is GeneralState.Loading -> RandomCardUiState.Loading
            is GeneralState.Success<T> -> RandomCardUiState.Success(data = data.toUi())
        }
}
