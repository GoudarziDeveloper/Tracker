package ir.tinyDeveloper.onboarding_presentation.screens.nutrient_goal

sealed class NutrientGoalEvent {
    data class OnCarbsRatioEnter(val ratio: String): NutrientGoalEvent()
    data class OnProteinRatioEnter(val ratio: String): NutrientGoalEvent()
    data class OnFatRatioEnter(val ratio: String): NutrientGoalEvent()
    object OnNextClicked: NutrientGoalEvent()
}
