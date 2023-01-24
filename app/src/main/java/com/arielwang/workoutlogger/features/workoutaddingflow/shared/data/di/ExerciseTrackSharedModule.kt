package com.arielwang.workoutlogger.features.workoutaddingflow.shared.data.di

import com.arielwang.workoutlogger.features.workoutaddingflow.shared.data.ExerciseTrackSharedStateManagerImpl
import com.arielwang.workoutlogger.features.workoutaddingflow.shared.domain.ExerciseTrackSharedStateManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class ExerciseTrackSharedModule {
    @Binds
    abstract fun bindExerciseSharedManager(
        impl: ExerciseTrackSharedStateManagerImpl,
    ): ExerciseTrackSharedStateManager
}