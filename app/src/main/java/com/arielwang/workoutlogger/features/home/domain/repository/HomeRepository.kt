package com.arielwang.workoutlogger.features.home.domain.repository

import com.arielwang.workoutlogger.database.model.WorkoutData


interface HomeRepository {
  suspend fun getAllWorkoutData(): List<WorkoutData>
}