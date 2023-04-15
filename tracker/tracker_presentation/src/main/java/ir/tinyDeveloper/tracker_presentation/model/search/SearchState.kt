package ir.tinyDeveloper.tracker_presentation.model.search

data class SearchState(
    val query: String = "",
    val isHIntVisible: Boolean = false,
    val isSearching: Boolean = false,
    val trackableFood: List<TrackableFoodUiState> = emptyList()
)
