package com.arielwang.workoutlogger.features.workoutaddingflow.shared.data

import com.arielwang.workoutlogger.database.model.WorkoutData
import com.arielwang.workoutlogger.features.workoutaddingflow.shared.domain.ExerciseTrackSharedStateManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExerciseTrackSharedStateManagerImpl @Inject constructor(): ExerciseTrackSharedStateManager {

    private var state: WorkoutData? = null

    override fun updateState(state: WorkoutData) {
        this.state = state
    }

    override fun getState(): WorkoutData {
        return checkNotNull(state)
    }
}
