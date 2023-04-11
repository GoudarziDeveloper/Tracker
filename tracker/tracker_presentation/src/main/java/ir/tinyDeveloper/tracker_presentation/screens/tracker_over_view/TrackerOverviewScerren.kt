package ir.tinyDeveloper.tracker_presentation.screens.tracker_over_view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import ir.tinyDeveloper.core.util.UiEvent
import ir.tinyDeveloper.core_ui.LocalSizing
import ir.tinyDeveloper.core_ui.LocalSpacing
import ir.tinyDeveloper.tracker_presentation.TrackerOverviewViewModel
import ir.tinyDeveloper.tracker_presentation.model.TrackerOverviewEvent
import ir.tinyDeveloper.tracker_presentation.screens.tracker_over_view.components.DaySelector
import ir.tinyDeveloper.tracker_presentation.screens.tracker_over_view.components.NutrientsHeader

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TrackerOverviewScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: TrackerOverviewViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val state = viewModel.state

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = spacing.spaceMedium)
    ) {
        item {
            NutrientsHeader(state = state)
            DaySelector(
                date = state.date,
                onPreviousDayClicked = {
                    viewModel.onEvent(TrackerOverviewEvent.OnPreviousDayClicked)
                },
                onNextDayClicked = {
                    viewModel.onEvent(TrackerOverviewEvent.OnNextDayClicked)
                },
                modifier = Modifier.fillMaxWidth().padding(spacing.spaceMedium)
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
        }
    }
}