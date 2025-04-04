package com.example.cards.basecards

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
import ru.pet.geek.VisibilityItemImpl
import ru.pet.geek.core.GeneralState
import ru.pet.geek.core.LocalResponse
import ru.pet.geek.core.mappers.toGeneralState
import ru.pet.geek.utils.UiInterface
import ru.pet.geek.widgets.CircleButtonInfo
import ru.pet.geek.widgets.LeftRightButton

abstract class BaseRandomCardViewModel<T> : BaseCardViewModel<T>() {
    protected val currentIndex = MutableStateFlow(0)
    protected val responseList = MutableStateFlow<List<LocalResponse<T>>>(mutableListOf())

    private val leftButtonState: LeftRightButton.LeftButton = LeftRightButton.LeftButton(onClick = ::onPreviousClick)
    private val rightButtonState: LeftRightButton.RightButton = LeftRightButton.RightButton(onClick = ::onNextClick)
    protected val buttonsState =
        MutableStateFlow<LeftRightButtonsWidgetState>(
            LeftRightButtonsWidgetStateImpl(
                leftButton = VisibilityItemImpl(isVisible = false, item = leftButtonState),
                rightButton = VisibilityItemImpl(isVisible = false, item = rightButtonState),
            ),
        )
    val buttonsUiState = buttonsState.asStateFlow()

    private val mutex = Mutex()

    protected val isHasNext: Boolean
        get() = true

    protected val isHasPrevious: Boolean
        get() = currentIndex.value >= 1

    override fun onInit() {
        super.onInit()

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
                .map { state ->
                    val newButtonsState =
                        when (state) {
                            is GeneralState.Loading, is GeneralState.Error -> {
                                LeftRightButtonsWidgetStateImpl(
                                    leftButton = VisibilityItemImpl(isVisible = false, item = leftButtonState),
                                    rightButton = VisibilityItemImpl(isVisible = false, item = rightButtonState),
                                )
                            }

                            is GeneralState.Success<T> ->
                                LeftRightButtonsWidgetStateImpl(
                                    leftButton = VisibilityItemImpl(isVisible = isHasPrevious, item = leftButtonState),
                                    rightButton = VisibilityItemImpl(isVisible = isHasNext, item = rightButtonState),
                                )
                        }
                    newButtonsState
                }.collect {
                    buttonsState.emit(it)
                }
        }
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

    override fun loadData() {
        val indexToResponse = currentIndex.value
        val data = viewModelScope.async { getData() }
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
        closeGenresWidget()
    }

    protected fun onPreviousClick() {
        if (currentIndex.value > 0) {
            currentIndex.value -= 1
        }
        closeGenresWidget()
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
