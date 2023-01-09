package com.arielwang.workoutlogger.features.home.data.repository

import com.arielwang.workoutlogger.database.ExerciseDao
import com.arielwang.workoutlogger.database.model.Exercise
import com.arielwang.workoutlogger.features.home.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val dao: ExerciseDao): HomeRepository {
  override suspend fun getAllExercises(): List<Exercise> {
    return dao.getAll()
  }
}