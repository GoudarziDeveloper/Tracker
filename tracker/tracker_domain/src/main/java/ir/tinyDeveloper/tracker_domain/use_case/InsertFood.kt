package ir.tinyDeveloper.tracker_domain.use_case

import ir.tinyDeveloper.tracker_domain.model.MealType
import ir.tinyDeveloper.tracker_domain.model.TrackableFood
import ir.tinyDeveloper.tracker_domain.model.TrackedFood
import ir.tinyDeveloper.tracker_domain.repository.TrackerRepository
import java.time.LocalDate
import kotlin.math.roundToInt

class InsertFood(private val repository: TrackerRepository){
    suspend operator fun invoke(
        food: TrackableFood,
        mealType: MealType,
        amount: Int,
        date: LocalDate
    ) {
        repository.insertTrackedFood(
            TrackedFood(
                name = food.name,
                carbs = ((food.carbsPer100g / 100f) * amount).roundToInt(),
                protein = ((food.proteinPer100g / 100f) * amount).roundToInt(),
                fat = ((food.fatPer100g / 100f) * amount).roundToInt(),
                calories = ((food.caloriesPer100g / 100f) * amount).roundToInt(),
                imageUrl = food.imageUrl,
                amount = amount,
                mealType = mealType,
                date = date
            )
        )
    }
}