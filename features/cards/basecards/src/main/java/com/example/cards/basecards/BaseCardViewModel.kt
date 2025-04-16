package com.example.cards.basecards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.pet.geek.core.GeneralState
import ru.pet.geek.core.LocalResponse
import ru.pet.geek.core.mappers.toGeneralState
import ru.pet.geek.domain.entities.dto.EntryModel
import ru.pet.geek.domain.entities.interfaces.IdHolder
import ru.pet.geek.widgets.CircleStaticLoadingButton
import ru.pet.geek.widgets.TitledHorisontalCorouselUiInfo

abstract class BaseCardViewModel<DATA : IdHolder> : ViewModel() {
    protected abstract fun DATA.toUi(isOpenGenresWidget: Boolean): SuccessUiState

    protected abstract suspend fun getData(): LocalResponse<DATA>

    protected abstract suspend fun getRecommendations(mangaId: Int): GeneralState<List<EntryModel>>

    protected abstract suspend fun getFavoritesIdsFlow(): Flow<List<Int>>

    protected abstract fun List<EntryModel>.toRecommendationsCarouselUI(): TitledHorisontalCorouselUiInfo

    protected val currentDataState = MutableStateFlow<GeneralState<DATA>>(GeneralState.Loading)

    protected val mutableUiState = MutableStateFlow<RandomCardUiState>(RandomCardUiState.Loading)
    val uiState = mutableUiState.asStateFlow()

    private val isOpenGenresWidgetMutableState = MutableStateFlow(false)

    protected val mutableRecommendationsState: MutableStateFlow<GeneralState<List<EntryModel>>> = MutableStateFlow(GeneralState.Loading)

    protected val mutableRecommendationsUIState: MutableStateFlow<GeneralState<TitledHorisontalCorouselUiInfo>> =
        MutableStateFlow(GeneralState.Loading)
    val recommendationsUIState: StateFlow<GeneralState<TitledHorisontalCorouselUiInfo>> = mutableRecommendationsUIState.asStateFlow()

    open fun onInit() {
        viewModelScope.launch {
            combine(
                currentDataState,
                isOpenGenresWidgetMutableState,
            ) { state, isGenresOpen ->
                state to isGenresOpen
            }.map { (dataState, isGenresOpen) -> dataState.toUiState(isOpenGenresWidget = isGenresOpen) }
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

    protected fun GeneralState<DATA>.toUiState(isOpenGenresWidget: Boolean): RandomCardUiState =
        when (this) {
            is GeneralState.Error ->
                RandomCardUiState.Error(
                    e = e,
                    uiInfo = CircleStaticLoadingButton(onClick = ::onRefreshClick),
                )

            is GeneralState.Loading -> RandomCardUiState.Loading
            is GeneralState.Success<DATA> -> RandomCardUiState.Success(data = data.toUi(isOpenGenresWidget))
        }

    protected fun closeGenresWidget() {
        isOpenGenresWidgetMutableState.value = false
    }

    protected fun openGenresWidget() {
        isOpenGenresWidgetMutableState.value = true
    }
}
