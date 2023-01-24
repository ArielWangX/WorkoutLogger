package com.arielwang.workoutlogger.features.home.domain.repository

import com.arielwang.workoutlogger.database.model.WorkoutAddingFlow


interface HomeRepository {
  suspend fun getAllExercises(): List<WorkoutAddingFlow>
}