package com.arielwang.workoutlogger.features.workoutaddingflow.shared.domain

import com.arielwang.workoutlogger.database.model.WorkoutData

interface ExerciseTrackSharedStateManager {

    fun updateState(state: WorkoutData)

    fun getState(): WorkoutData
}