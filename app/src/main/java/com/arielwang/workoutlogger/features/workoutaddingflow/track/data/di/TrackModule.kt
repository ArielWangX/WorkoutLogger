package com.arielwang.workoutlogger.features.workoutaddingflow.track.data.di

import com.arielwang.workoutlogger.features.workoutaddingflow.track.data.repository.TrackRepositoryImpl
import com.arielwang.workoutlogger.features.workoutaddingflow.track.domain.repository.TrackRepository
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