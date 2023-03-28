package ir.tinyDeveloper.trackermultimodulearchitecture.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.tinyDeveloper.core.domain.use_case.DigitsAndDotLength
import ir.tinyDeveloper.core.domain.use_case.FilterOutDigits
import ir.tinyDeveloper.core.domain.use_case.FilterOutDigitsAndDot
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Provides
    @Singleton
    fun provideFilterOutDigitsUseCase(): FilterOutDigits {
        return FilterOutDigits()
    }

    @Provides
    @Singleton
    fun provideFilterOutDigitsAndDot(): FilterOutDigitsAndDot {
        return FilterOutDigitsAndDot()
    }

    @Provides
    @Singleton
    fun provideDigitsAndDotLength(): DigitsAndDotLength = DigitsAndDotLength()
}