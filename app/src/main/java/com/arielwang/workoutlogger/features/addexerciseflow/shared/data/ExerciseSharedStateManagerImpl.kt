package com.arielwang.workoutlogger.features.addexerciseflow.shared.data

import com.arielwang.workoutlogger.database.model.ExerciseFlow
import com.arielwang.workoutlogger.features.addexerciseflow.shared.domain.ExerciseSharedStateManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExerciseSharedStateManagerImpl @Inject constructor(): ExerciseSharedStateManager {

    private var state: ExerciseFlow? = null

    override fun updateState(state: ExerciseFlow) {
        this.state = state
    }

    override fun getState(): ExerciseFlow {
        return checkNotNull(state)
    }

    override fun deleteState() {
        state = null
    }
}
