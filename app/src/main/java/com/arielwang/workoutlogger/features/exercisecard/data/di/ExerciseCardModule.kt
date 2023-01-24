package com.arielwang.workoutlogger.features.exercisecard.data.di

import com.arielwang.workoutlogger.features.exercisecard.data.repository.ExerciseCardRepositoryImpl
import com.arielwang.workoutlogger.features.exercisecard.domain.ExerciseCardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class ExerciseCardModule {
    @Binds
    abstract fun bindExerciseDetailRepository(
        impl: ExerciseCardRepositoryImpl
    ): ExerciseCardRepository
}