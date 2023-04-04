package ir.tinyDeveloper.tracker_domain.use_case

data class TrackerUseCase(
    val calculateMealNutrients: CalculateMealNutrients,
    val insertFood: InsertFood,
    val deleteTrackedFood: DeleteTrackedFood,
    val searchFood: SearchFood,
    val getFoodsForDay: GetFoodsForDay
)