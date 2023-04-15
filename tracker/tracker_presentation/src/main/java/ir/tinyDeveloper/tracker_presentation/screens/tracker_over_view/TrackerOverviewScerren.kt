package ir.tinyDeveloper.tracker_presentation.screens.tracker_over_view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ir.tinyDeveloper.core.R
import ir.tinyDeveloper.core.util.UiEvent
import ir.tinyDeveloper.core_ui.LocalSpacing
import ir.tinyDeveloper.tracker_presentation.model.Meal
import ir.tinyDeveloper.tracker_presentation.model.tracker_over_view.TrackerOverviewEvent
import ir.tinyDeveloper.tracker_presentation.screens.tracker_over_view.components.AddButton
import ir.tinyDeveloper.tracker_presentation.screens.tracker_over_view.components.DaySelector
import ir.tinyDeveloper.tracker_presentation.screens.tracker_over_view.components.ExpandableMeal
import ir.tinyDeveloper.tracker_presentation.screens.tracker_over_view.components.NutrientsHeader
import ir.tinyDeveloper.tracker_presentation.screens.tracker_over_view.components.TrackedFoodItem

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TrackerOverviewScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: TrackerOverviewViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val state = viewModel.state

    LaunchedEffect(key1 = Unit){
        viewModel.uiEvent.collect{ event ->
            when(event){
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = spacing.spaceSmall)
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.spaceMedium)
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
        }
        items(state.meals) { meal: Meal ->
            ExpandableMeal(
                meal = meal,
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = spacing.spaceSmall)
                    ) {
                        state.trackedFoods.forEach { food ->
                            TrackedFoodItem(
                                trackedFood = food,
                                onDeleteClicked = {
                                    viewModel.onEvent(
                                        TrackerOverviewEvent.OnDeleteTrackerFoodClicked(food)
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.height(spacing.spaceMedium))
                        }
                        AddButton(
                            text = stringResource(
                                id = R.string.add_meal,
                                meal.name.asString(context)
                            ),
                            onClick = { viewModel.onEvent(TrackerOverviewEvent.OnAddFoodClicked(meal)) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                onToggleClicked = { viewModel.onEvent(TrackerOverviewEvent.OnToggleMealClicked(meal)) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}