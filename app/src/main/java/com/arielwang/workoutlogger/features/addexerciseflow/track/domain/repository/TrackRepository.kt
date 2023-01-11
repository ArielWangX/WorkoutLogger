package com.arielwang.workoutlogger.features.addexerciseflow.track.domain.repository

import com.arielwang.workoutlogger.database.model.ExerciseFlow

interface TrackRepository {
    suspend fun insertExercise(exercise: ExerciseFlow)
}