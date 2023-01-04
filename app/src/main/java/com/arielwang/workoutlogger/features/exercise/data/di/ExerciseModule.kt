package com.arielwang.workoutlogger.features.exercise.data.di

import com.arielwang.workoutlogger.features.exercise.data.repository.ExerciseRepositoryImpl
import com.arielwang.workoutlogger.features.exercise.repository.ExerciseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class ExerciseModule {
  @Binds
  abstract fun bindExerciseRepository(
    impl: ExerciseRepositoryImpl,
  ): ExerciseRepository
}