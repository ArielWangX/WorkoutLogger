package com.arielwang.workoutlogger.features.addexerciseflow.shared.data.di

import com.arielwang.workoutlogger.features.addexerciseflow.shared.data.ExerciseSharedStateManagerImpl
import com.arielwang.workoutlogger.features.addexerciseflow.shared.domain.ExerciseSharedStateManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class ExerciseSharedModule {
    @Binds
    abstract fun bindExerciseSharedManager(
        impl: ExerciseSharedStateManagerImpl,
    ): ExerciseSharedStateManager
}