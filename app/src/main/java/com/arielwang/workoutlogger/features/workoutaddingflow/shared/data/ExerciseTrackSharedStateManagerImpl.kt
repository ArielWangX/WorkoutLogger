package com.arielwang.workoutlogger.features.workoutaddingflow.shared.data

import com.arielwang.workoutlogger.database.model.WorkoutAddingFlow
import com.arielwang.workoutlogger.features.workoutaddingflow.shared.domain.ExerciseTrackSharedStateManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExerciseTrackSharedStateManagerImpl @Inject constructor(): ExerciseTrackSharedStateManager {

    private var state: WorkoutAddingFlow? = null

    override fun updateState(state: WorkoutAddingFlow) {
        this.state = state
    }

    override fun getState(): WorkoutAddingFlow {
        return checkNotNull(state)
    }
}
