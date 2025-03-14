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
import ru.pet.geek.core.mappers.map
import ru.pet.geek.core.mappers.toGeneralState

abstract class BaseRandomCardViewModel<T> : ViewModel() {
    protected abstract suspend fun getRandomData(): LocalResponse<T>

    protected abstract fun T.toUi(): SuccessUiState

    protected val currentIndex = MutableStateFlow(0)
    protected val responseList = MutableStateFlow<List<LocalResponse<T>>>(mutableListOf())

    protected val currentDataState = MutableStateFlow<GeneralState<T>>(GeneralState.Loading)

    protected val mutableUiState = MutableStateFlow<GeneralState<SuccessUiState>>(GeneralState.Loading)
    val uiState = mutableUiState.asStateFlow()
    private val mutex = Mutex()

    init {
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
                .map { dataState -> dataState.map { data -> data.toUi() } }
                .collect { uiState ->
                    mutableUiState.emit(uiState)
                }
        }

        viewModelScope.launch {
        }
    }

    private fun refresh() {
        currentDataState.value = GeneralState.Loading
        loadData()
    }

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
}
