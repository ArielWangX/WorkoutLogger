package com.arielwang.workoutlogger.features.home.domain.repository

import com.arielwang.workoutlogger.database.model.Exercise


interface HomeRepository {
  suspend fun getAllExercises(): List<Exercise>
}