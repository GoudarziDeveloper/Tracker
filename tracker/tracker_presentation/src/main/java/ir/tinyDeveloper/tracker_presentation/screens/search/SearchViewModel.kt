package ir.tinyDeveloper.tracker_presentation.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.tinyDeveloper.core.R
import ir.tinyDeveloper.core.domain.use_case.FilterOutDigits
import ir.tinyDeveloper.core.util.UiEvent
import ir.tinyDeveloper.core.util.UiText
import ir.tinyDeveloper.tracker_domain.use_case.TrackerUseCase
import ir.tinyDeveloper.tracker_presentation.model.search.SearchEvent
import ir.tinyDeveloper.tracker_presentation.model.search.SearchState
import ir.tinyDeveloper.tracker_presentation.model.search.TrackableFoodUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val trackerUseCase: TrackerUseCase,
    private val filterOutDigits: FilterOutDigits
) : ViewModel() {
    var state by mutableStateOf(SearchState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnQueryChange -> {
                state = state.copy(query = event.query)
            }

            is SearchEvent.OnSearch -> {
                searchFood()
            }

            is SearchEvent.OnAmountForFoodChange -> {
                state = state.copy(trackableFood = state.trackableFood.map {
                    if (event.food == it.food) it.copy(amount = filterOutDigits(event.amount)) else it
                })
            }

            is SearchEvent.OnSearchFocusChange -> {
                state = state.copy(isHIntVisible = !event.isFocus && state.query.isBlank())
            }

            is SearchEvent.OnToggleTrackableFood -> {
                state = state.copy(trackableFood = state.trackableFood.map {
                    if (event.food == it.food) it.copy(isExpanded = !it.isExpanded) else it
                })
            }

            is SearchEvent.OnTrackFoodClick -> {
                trackFood(event)
            }
        }
    }

    private fun searchFood() {
        viewModelScope.launch {
            state = state.copy(isSearching = true, trackableFood = emptyList())
            trackerUseCase.searchFood(state.query)
                .onSuccess { foods ->
                    state = state.copy(
                        isSearching = false,
                        trackableFood = foods.map { TrackableFoodUiState(it) },
                        query = ""
                    )
                }
                .onFailure {
                    state = state.copy(isSearching = false)
                    _uiEvent.send(UiEvent.ShowSnackBar(UiText.StringResource(R.string.error_something_went_wrong)))
                }
        }
    }

    private fun trackFood(event: SearchEvent.OnTrackFoodClick) {
        viewModelScope.launch {
            val uiEvent = state.trackableFood.find { it.food == event.food }
            trackerUseCase.insertFood(
                food = uiEvent?.food ?: return@launch,
                amount = uiEvent.amount.toIntOrNull() ?: return@launch,
                mealType = event.mealType,
                date = event.date
            )
            _uiEvent.send(UiEvent.NavigateUp)
        }
    }
}