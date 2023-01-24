package com.arielwang.workoutlogger.features.home.data.repository

import com.arielwang.workoutlogger.database.daos.WorkoutDao
import com.arielwang.workoutlogger.database.model.WorkoutAddingFlow
import com.arielwang.workoutlogger.features.home.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val dao: WorkoutDao): HomeRepository {
  override suspend fun getAllExercises(): List<WorkoutAddingFlow> {
    return dao.getAllExercise()
  }
}