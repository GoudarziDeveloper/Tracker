package ir.tinyDeveloper.tracker_domain.use_case

import ir.tinyDeveloper.tracker_domain.model.TrackedFood
import ir.tinyDeveloper.tracker_domain.repository.TrackerRepository

class DeleteTrackedFood(private val repository: TrackerRepository) {
    suspend operator fun invoke(trackedFood: TrackedFood) {
        repository.deleteTrackedFood(trackedFood)
    }
}