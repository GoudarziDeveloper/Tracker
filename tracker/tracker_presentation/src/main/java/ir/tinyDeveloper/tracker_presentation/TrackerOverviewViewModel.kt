package ir.tinyDeveloper.tracker_presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.tinyDeveloper.core.domain.preferences.Preferences
import ir.tinyDeveloper.core.navigation.Routes
import ir.tinyDeveloper.core.util.UiEvent
import ir.tinyDeveloper.tracker_domain.use_case.TrackerUseCase
import ir.tinyDeveloper.tracker_presentation.model.TrackerOverviewState
import ir.tinyDeveloper.tracker_presentation.model.TrackerOverviewEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
    private val preferences: Preferences,
    private val useCases: TrackerUseCase
) : ViewModel() {
    var state by mutableStateOf(TrackerOverviewState())

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var getFoodsForDateJob: Job? = null

    init {
        preferences.saveShouldShowOnBoarding(false)
    }

    fun onEvent(event: TrackerOverviewEvent) {
        when (event) {
            is TrackerOverviewEvent.OnAddFoodClicked -> {
                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.Navigate(
                            route = Routes.SEARCH
                                    + "/${event.meal.name}"
                                    + "/${state.date.dayOfMonth}"
                                    + "/${state.date.month}"
                                    + "/${state.date.year}"
                        )
                    )
                }
            }

            is TrackerOverviewEvent.OnDeleteTrackerFoodClicked -> {
                viewModelScope.launch {
                    useCases.deleteTrackedFood(event.trackedFood)
                    refreshFood()
                }
            }

            is TrackerOverviewEvent.OnNextDayClicked -> {
                state = state.copy(date = state.date.plusDays(1))
                refreshFood()
            }

            is TrackerOverviewEvent.OnPreviousDayClicked -> {
                state = state.copy(date = state.date.minusDays(1))
                refreshFood()
            }

            is TrackerOverviewEvent.OnToggleMealClicked -> {
                state = state.copy(meals = state.meals.map {
                    if (it.name == event.meal.name) it.copy(isExpended = !it.isExpended) else it
                })
            }
        }
    }

    private fun refreshFood() {
        val userInfo = preferences.loadUserInfo()
        getFoodsForDateJob?.cancel()
        getFoodsForDateJob = useCases.getFoodsForDay(state.date).onEach { foods ->
            val nutrientResult = useCases.calculateMealNutrients(foods, userInfo)
            state = state.copy(
                totalCarbs = nutrientResult.totalCarbs,
                totalProtein = nutrientResult.totalProtein,
                totalFat = nutrientResult.totalFat,
                totalCalories = nutrientResult.totalCalories,
                carbsGoal = nutrientResult.carbsGoal,
                proteinGoal = nutrientResult.proteinGoal,
                fatGoal = nutrientResult.fatGoal,
                caloriesGoal = nutrientResult.caloriesGoal,
                trackedFoods = foods,
                meals = state.meals.map {
                    val nutrientForMeal = nutrientResult.mealNutrients[it.mealType]
                        ?: return@map it.copy(carbs = 0, protein = 0, fat = 0, calories = 0)
                    it.copy(
                        carbs = nutrientForMeal.carbs,
                        protein = nutrientForMeal.protein,
                        fat = nutrientForMeal.fat,
                        calories = nutrientForMeal.calories
                    )
                }
            )
        }.launchIn(viewModelScope)
    }
}