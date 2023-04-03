package ir.tinyDeveloper.onboarding_presentation.screens.height

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.tinyDeveloper.core.R
import ir.tinyDeveloper.core.domain.preferences.Preferences
import ir.tinyDeveloper.core.domain.use_case.FilterOutDigits
import ir.tinyDeveloper.core.navigation.Routes
import ir.tinyDeveloper.core.util.UiEvent
import ir.tinyDeveloper.core.util.UiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits
): ViewModel() {
    var height by mutableStateOf("180")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onHeightChange(height: String) {
        this.height = filterOutDigits(height, 3)
    }

    fun onNextClicked() {
        viewModelScope.launch {
            val heightNumber = height.toIntOrNull()?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackBar(
                        UiText.StringResource(R.string.error_height_cant_be_empty)
                    )
                )
                return@launch
            }
            preferences.saveHeight(height = heightNumber)
            _uiEvent.send(UiEvent.Navigate(Routes.WEIGHT))
        }
    }
}