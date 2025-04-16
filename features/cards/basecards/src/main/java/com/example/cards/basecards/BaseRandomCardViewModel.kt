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
import ru.pet.geek.core.mappers.map
import ru.pet.geek.core.mappers.toGeneralState
import ru.pet.geek.core.utils.ParentException
import ru.pet.geek.domain.entities.dto.EntryModel
import ru.pet.geek.domain.entities.interfaces.IdHolder
import ru.pet.geek.utils.UiInterface
import ru.pet.geek.widgets.CircleButtonInfo
import ru.pet.geek.widgets.LeftRightButton

abstract class BaseRandomCardViewModel<DATA : IdHolder> : BaseCardViewModel<DATA>() {
    protected val currentIndex = MutableStateFlow(0)
    protected val responseList = MutableStateFlow<List<LocalResponse<DATA>>>(mutableListOf())

    private val mutableRecommendationsListFlow: MutableStateFlow<Map<Int, GeneralState<List<EntryModel>>>> =
        MutableStateFlow(mutableMapOf())

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
                currentDataState,
                mutableRecommendationsListFlow,
            ) { currentState, recom ->
                currentState to recom
            }.map { (state, recom) ->
                when (state) {
                    is GeneralState.Error -> GeneralState.Error(ParentException())
                    is GeneralState.Loading -> GeneralState.Loading
                    is GeneralState.Success<DATA> -> {
                        when (val recomData = recom[state.data.malId]) {
                            is GeneralState.Error, is GeneralState.Loading, is GeneralState.Success<List<EntryModel>> -> recomData
                            null -> {
                                val map = mutableRecommendationsListFlow.value
                                val data = async { getRecommendations(state.data.malId) }
                                launch {
                                    val newMap =
                                        buildMap {
                                            putAll(map)
                                            put(state.data.malId, GeneralState.Loading)
                                        }
                                    mutableRecommendationsListFlow.emit(newMap)
                                }
                                launch {
                                    val responsedMap =
                                        buildMap {
                                            putAll(mutableRecommendationsListFlow.value)
                                            put(state.data.malId, data.await())
                                        }
                                    mutableRecommendationsListFlow.emit(responsedMap)
                                }
                                GeneralState.Loading
                            }
                        }
                    }
                }
            }.collect { currentRecom ->
                mutableRecommendationsState.emit(currentRecom)
            }
        }

        viewModelScope.launch {
            mutableRecommendationsState
                .map { state ->
                    when (state) {
                        is GeneralState.Error -> state
                        is GeneralState.Loading -> state
                        is GeneralState.Success<List<EntryModel>> -> {
                            state.map { modelsList ->
                                modelsList.toRecommendationsCarouselUI()
                            }
                        }
                    }
                }.collect {
                    mutableRecommendationsUIState.emit(it)
                }
        }

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

                            is GeneralState.Success<DATA> ->
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
        response: LocalResponse<DATA>,
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
