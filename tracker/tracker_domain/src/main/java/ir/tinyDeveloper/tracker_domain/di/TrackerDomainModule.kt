package ir.tinyDeveloper.tracker_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ir.tinyDeveloper.tracker_domain.repository.TrackerRepository
import ir.tinyDeveloper.tracker_domain.use_case.CalculateMealNutrients
import ir.tinyDeveloper.tracker_domain.use_case.DeleteTrackedFood
import ir.tinyDeveloper.tracker_domain.use_case.GetFoodsForDay
import ir.tinyDeveloper.tracker_domain.use_case.InsertFood
import ir.tinyDeveloper.tracker_domain.use_case.SearchFood
import ir.tinyDeveloper.tracker_domain.use_case.TrackerUseCase

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {
    @Provides
    @ViewModelScoped
    fun provideTrackerUseCase(repository: TrackerRepository): TrackerUseCase {
        return TrackerUseCase(
            calculateMealNutrients = CalculateMealNutrients(),
            insertFood = InsertFood(repository),
            deleteTrackedFood = DeleteTrackedFood(repository),
            searchFood = SearchFood(repository),
            getFoodsForDay = GetFoodsForDay(repository)
        )
    }
}