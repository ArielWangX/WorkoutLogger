package com.arielwang.workoutlogger.features.home.domain.repository

import com.arielwang.workoutlogger.database.model.WorkoutData
import kotlinx.coroutines.flow.Flow


interface HomeRepository {
  suspend fun getAllWorkoutData(): List<WorkoutData>

  fun getAllWorkoutDataFlow(): Flow<List<WorkoutData>>
}