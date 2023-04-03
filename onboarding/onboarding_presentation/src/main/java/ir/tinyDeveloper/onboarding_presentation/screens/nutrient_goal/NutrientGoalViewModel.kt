package ir.tinyDeveloper.onboarding_presentation.screens.nutrient_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.tinyDeveloper.core.domain.preferences.Preferences
import ir.tinyDeveloper.core.domain.use_case.FilterOutDigits
import ir.tinyDeveloper.core.navigation.Routes
import ir.tinyDeveloper.core.util.UiEvent
import ir.tinyDeveloper.onboarding_domain.use_case.ValidateNutrients
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits,
    private val validateNutrients: ValidateNutrients
): ViewModel(){
    var state by mutableStateOf(NutrientGoalState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: NutrientGoalEvent){
        when(event){
            is NutrientGoalEvent.OnCarbsRatioEnter -> {
                state = state.copy(carbsRatio = filterOutDigits(event.ratio, 2))
            }
            is NutrientGoalEvent.OnProteinRatioEnter -> {
                state = state.copy(proteinRatio = filterOutDigits(event.ratio, 2))
            }
            is NutrientGoalEvent.OnFatRatioEnter -> {
                state = state.copy(fatRatio = filterOutDigits(event.ratio, 2))
            }
            is NutrientGoalEvent.OnNextClicked -> {
                val result = validateNutrients(
                    carbsRatioText = state.carbsRatio,
                    proteinRatioText = state.proteinRatio,
                    fatRatioText = state.fatRatio
                )
                when(result){
                    is ValidateNutrients.Result.Success -> {
                        preferences.saveCarbRatio(result.carbsRatio)
                        preferences.saveProteinRatio(result.proteinRatio)
                        preferences.saveFatRatio(result.fatRatio)
                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.Navigate(Routes.TRACKER_OVERVIEW))
                        }
                    }
                    is ValidateNutrients.Result.Error -> {
                        viewModelScope.launch { _uiEvent.send(UiEvent.ShowSnackBar(result.message)) }
                    }
                }
            }
        }
    }
}