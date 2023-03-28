package ir.tinyDeveloper.onboarding_presentation.screens.weight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.tinyDeveloper.core.R
import ir.tinyDeveloper.core.domain.preferences.Preferences
import ir.tinyDeveloper.core.domain.use_case.DigitsAndDotLength
import ir.tinyDeveloper.core.domain.use_case.FilterOutDigitsAndDot
import ir.tinyDeveloper.core.navigation.Routes
import ir.tinyDeveloper.core.util.UiEvent
import ir.tinyDeveloper.core.util.UiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigitsAndDot: FilterOutDigitsAndDot,
    private val digitsAndDotLength: DigitsAndDotLength
): ViewModel(){
    var weight by mutableStateOf("80.0")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onWeightChange(weight: String) {
        if(weight.length <= digitsAndDotLength(weight)){
            this.weight = filterOutDigitsAndDot(weight)
        }
    }

    fun onNextClicked() {
        viewModelScope.launch {
            val weightNumber = weight.toFloatOrNull()?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackBar(
                        UiText.StringResource(R.string.error_weight_cant_be_empty)
                    )
                )
                return@launch
            }
            preferences.saveWeight(weight = weightNumber)
            _uiEvent.send(UiEvent.Navigate(Routes.ACTIVITY))
        }
    }
}