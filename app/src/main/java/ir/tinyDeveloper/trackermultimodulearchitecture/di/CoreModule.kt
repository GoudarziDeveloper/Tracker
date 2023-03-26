package ir.tinyDeveloper.trackermultimodulearchitecture.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.tinyDeveloper.core.domain.use_case.FilterOutDigits
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Provides
    @Singleton
    fun provideFilterOutDigitsUseCase(): FilterOutDigits {
        return FilterOutDigits()
    }
}