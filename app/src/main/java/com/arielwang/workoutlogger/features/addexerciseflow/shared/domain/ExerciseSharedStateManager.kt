package com.arielwang.workoutlogger.features.addexerciseflow.shared.domain

import com.arielwang.workoutlogger.database.model.ExerciseFlow

interface ExerciseSharedStateManager {

    fun updateState(state: ExerciseFlow)

    fun getState(): ExerciseFlow

    fun deleteState()
}