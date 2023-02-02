package com.arielwang.workoutlogger.features.workoutaddingflow.track.domain.repository

import com.arielwang.workoutlogger.database.model.WorkoutData

interface TrackRepository {
    suspend fun insertWorkout(workoutData: WorkoutData)
}