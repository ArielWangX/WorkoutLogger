package com.arielwang.workoutlogger.features.exercise.data.repository

import com.arielwang.workoutlogger.database.ExerciseDao
import com.arielwang.workoutlogger.database.model.Exercise
import com.arielwang.workoutlogger.features.exercise.repository.ExerciseRepository
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(private val dao: ExerciseDao): ExerciseRepository {
  override suspend fun insertExercise(exercise: Exercise) {
    dao.insertExercise(exercise)
  }
}