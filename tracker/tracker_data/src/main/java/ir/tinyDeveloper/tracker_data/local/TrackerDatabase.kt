package ir.tinyDeveloper.tracker_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.tinyDeveloper.core.util.Constants.TRACKER_DATABASE_VERSION
import ir.tinyDeveloper.tracker_data.local.entity.TrackedFoodEntity

@Database(
    entities = [TrackedFoodEntity::class],
    version = TRACKER_DATABASE_VERSION
)
abstract class TrackerDatabase: RoomDatabase() {
    abstract val dao: TrackedDao
}