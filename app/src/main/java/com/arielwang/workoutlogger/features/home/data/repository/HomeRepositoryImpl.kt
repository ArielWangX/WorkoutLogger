package com.arielwang.workoutlogger.features.home.data.repository

import com.arielwang.workoutlogger.database.daos.ExerciseDao
import com.arielwang.workoutlogger.database.model.ExerciseFlow
import com.arielwang.workoutlogger.features.home.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val dao: ExerciseDao): HomeRepository {
  override suspend fun getAllExercises(): List<ExerciseFlow> {
    return dao.getAll()
  }
}