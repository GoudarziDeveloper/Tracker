package ir.tinyDeveloper.tracker_data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import ir.tinyDeveloper.tracker_data.local.entity.TrackedFoodEntity
import ir.tinyDeveloper.tracker_domain.model.MealType
import ir.tinyDeveloper.tracker_domain.model.TrackedFood
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
fun TrackedFoodEntity.toTrackedFood(): TrackedFood {
    return TrackedFood(
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        imageUrl = imgUrl,
        mealType = MealType.fromString(type),
        amount = amount,
        date = LocalDate.of(year, month, dayOfMonth),
        calories = calories,
        id = id
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun TrackedFood.toTrackedFoodEntity(): TrackedFoodEntity{
    return TrackedFoodEntity(
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        imgUrl = imageUrl,
        type = mealType.name,
        amount = amount,
        dayOfMonth = date.dayOfMonth,
        month = date.monthValue,
        year = date.year,
        calories = calories,
        id = id
    )
}