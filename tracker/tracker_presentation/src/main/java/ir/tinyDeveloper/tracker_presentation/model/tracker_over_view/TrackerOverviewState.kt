package ir.tinyDeveloper.tracker_presentation.model.tracker_over_view

import android.os.Build
import androidx.annotation.RequiresApi
import ir.tinyDeveloper.tracker_domain.model.TrackedFood
import ir.tinyDeveloper.tracker_presentation.model.Meal
import ir.tinyDeveloper.tracker_presentation.model.defaultMeals
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
data class TrackerOverviewState(
    val totalCarbs: Int = 0,
    val totalProtein: Int = 0,
    val totalFat: Int = 0,
    val totalCalories: Int = 0,
    val carbsGoal: Int = 0,
    val proteinGoal: Int = 0,
    val fatGoal: Int = 0,
    val caloriesGoal: Int = 0,
    val date: LocalDate = LocalDate.now(),
    val trackedFoods: List<TrackedFood> = emptyList(),
    val meals: List<Meal> = defaultMeals
)
