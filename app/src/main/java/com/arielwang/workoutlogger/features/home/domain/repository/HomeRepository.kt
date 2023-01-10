package com.arielwang.workoutlogger.features.home.domain.repository

import com.arielwang.workoutlogger.database.model.ExerciseFlow


interface HomeRepository {
  suspend fun getAllExercises(): List<ExerciseFlow>
}