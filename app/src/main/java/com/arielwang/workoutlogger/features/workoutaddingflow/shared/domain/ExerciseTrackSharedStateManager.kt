package com.arielwang.workoutlogger.features.workoutaddingflow.shared.domain

import com.arielwang.workoutlogger.database.model.WorkoutAddingFlow

interface ExerciseTrackSharedStateManager {

    fun updateState(state: WorkoutAddingFlow)

    fun getState(): WorkoutAddingFlow
}