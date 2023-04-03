package ir.tinyDeveloper.tracker_data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import ir.tinyDeveloper.tracker_data.local.TrackedDao
import ir.tinyDeveloper.tracker_data.mapper.toTrackableFood
import ir.tinyDeveloper.tracker_data.mapper.toTrackedFood
import ir.tinyDeveloper.tracker_data.mapper.toTrackedFoodEntity
import ir.tinyDeveloper.tracker_data.remote.OpenFoodApi
import ir.tinyDeveloper.tracker_domain.model.TrackableFood
import ir.tinyDeveloper.tracker_domain.model.TrackedFood
import ir.tinyDeveloper.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val dao: TrackedDao,
    private val api: OpenFoodApi
): TrackerRepository {
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
            val searchDto = api.searchFood(
                query = query,
                page = page,
                pageSize = pageSize
            )
            Result.success(searchDto.products.mapNotNull { it.toTrackableFood() })
        } catch (e: Exception){
            e.printStackTrace()
            Result.failure(e)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getFoodForDate(date: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodForDay(
            day = date.dayOfMonth,
            month = date.monthValue,
            year = date.year
        ).map { entities ->
            entities.map { it.toTrackedFood() }
        }
    }
}