package com.arielwang.workoutlogger.features.workoutaddingflow.track.domain.repository

import com.arielwang.workoutlogger.database.model.WorkoutAddingFlow

interface TrackRepository {
    suspend fun insertExercise(exercise: WorkoutAddingFlow)
}