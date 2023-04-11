package ir.tinyDeveloper.tracker_presentation.model

import ir.tinyDeveloper.tracker_domain.model.TrackedFood

sealed class TrackerOverviewEvent {
    object OnNextDayClicked: TrackerOverviewEvent()
    object OnPreviousDayClicked: TrackerOverviewEvent()
    data class OnToggleMealClicked(val meal: Meal): TrackerOverviewEvent()
    data class OnDeleteTrackerFoodClicked(val trackedFood: TrackedFood): TrackerOverviewEvent()
    data class OnAddFoodClicked(val meal: Meal): TrackerOverviewEvent() }
