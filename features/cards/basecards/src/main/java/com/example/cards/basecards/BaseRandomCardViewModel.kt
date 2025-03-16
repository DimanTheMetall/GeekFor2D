package com.example.cards.basecards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import ru.pet.geek.core.GeneralState
import ru.pet.geek.core.LocalResponse
import ru.pet.geek.core.mappers.toGeneralState
import ru.pet.geek.utils.UiInterface
import ru.pet.geek.widgets.CircleButtonInfo
import ru.pet.geek.widgets.CircleStaticLoadingButton

abstract class BaseRandomCardViewModel<T> : ViewModel() {
    protected abstract suspend fun getRandomData(): LocalResponse<T>

    protected abstract fun T.toUi(): SuccessUiState

    protected val currentIndex = MutableStateFlow(0)
    protected val responseList = MutableStateFlow<List<LocalResponse<T>>>(mutableListOf())

    protected val currentDataState = MutableStateFlow<GeneralState<T>>(GeneralState.Loading)

    protected val mutableUiState = MutableStateFlow<RandomCardUiState>(RandomCardUiState.Loading)
    val uiState = mutableUiState.asStateFlow()
    private val mutex = Mutex()

    protected val isHasNext: Boolean
        get() = currentIndex.value < responseList.value.lastIndex

    protected val isHasPrevious: Boolean
        get() = currentIndex.value <= 1

    protected fun onInit() {
        viewModelScope.launch {
            combine(
                currentIndex,
                responseList,
            ) { index, list ->
                list.getOrNull(index)
            }.collect { state ->
                if (state == null) addNewResponse()
                val newState = state?.toGeneralState() ?: GeneralState.Loading
                currentDataState.emit(newState)
            }
        }

        viewModelScope.launch {
            currentDataState
                .map { dataState -> dataState.toUiState() }
                .collect { uiState ->
                    mutableUiState.emit(uiState)
                }
        }
    }

    private fun refresh() {
        loadData()
    }

    private fun onRefreshClick() = refresh()

    private fun replaceResponseOnIndex(
        response: LocalResponse<T>,
        indexToReplace: Int = currentIndex.value,
    ) {
        viewModelScope.launch {
            mutex.withLock {
                val list = responseList.value.toMutableList()
                list.add(index = indexToReplace, element = response)
                responseList.emit(list)
            }
        }
    }

    private fun loadData() {
        val indexToResponse = currentIndex.value
        val data = viewModelScope.async { getRandomData() }
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = data.await()
                replaceResponseOnIndex(response = response, indexToReplace = indexToResponse)
            }
        }
    }

    private fun addNewResponse() {
        viewModelScope.launch {
            loadData()
        }
    }

    protected fun onNextClick() {
        when (currentDataState.value) {
            is GeneralState.Error -> refresh()
            is GeneralState.Success<*> -> {
                currentIndex.value += 1
            }

            is GeneralState.Loading -> Unit
        }
    }

    protected fun onPreviousClick() {
        if (currentIndex.value > 0) {
            currentIndex.value -= 1
        }
    }

    private fun GeneralState<T>.toUiState(): RandomCardUiState =
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

sealed interface RandomCardUiState : UiInterface {
    data class Success(
        val data: SuccessUiState,
    ) : RandomCardUiState

    data class Error(
        val e: Throwable,
        val uiInfo: CircleButtonInfo,
    ) : RandomCardUiState

    data object Loading : RandomCardUiState
}
