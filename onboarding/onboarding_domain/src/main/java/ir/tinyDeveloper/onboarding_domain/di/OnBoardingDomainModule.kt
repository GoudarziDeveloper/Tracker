package ir.tinyDeveloper.onboarding_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ir.tinyDeveloper.onboarding_domain.use_case.ValidateNutrients

@Module
@InstallIn(ViewModelComponent::class)
object OnBoardingDomainModule {
    @Provides
    @ViewModelScoped
    fun provideValidateNutrientGoalUseCase(): ValidateNutrients {
        return ValidateNutrients()
    }
}