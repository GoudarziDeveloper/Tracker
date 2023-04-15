package ir.tinyDeveloper.tracker_presentation.model.search

import ir.tinyDeveloper.tracker_domain.model.TrackableFood

data class TrackableFoodUiState(
    val food: TrackableFood,
    val isExpanded: Boolean = false,
    val amount: String = ""
)
