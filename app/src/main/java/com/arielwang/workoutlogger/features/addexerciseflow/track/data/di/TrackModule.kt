package com.arielwang.workoutlogger.features.addexerciseflow.track.data.di

import com.arielwang.workoutlogger.features.addexerciseflow.exercise.data.repository.ExerciseRepositoryImpl
import com.arielwang.workoutlogger.features.addexerciseflow.exercise.domain.repository.ExerciseRepository
import com.arielwang.workoutlogger.features.addexerciseflow.track.data.repository.TrackRepositoryImpl
import com.arielwang.workoutlogger.features.addexerciseflow.track.domain.repository.TrackRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class TrackModule {
    @Binds
    abstract fun bindTrackRepository(
        impl: TrackRepositoryImpl,
    ): TrackRepository
}