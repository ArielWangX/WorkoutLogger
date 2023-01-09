package com.arielwang.workoutlogger.features.exercise.repository

import com.arielwang.workoutlogger.database.model.Exercise


interface ExerciseRepository {
  suspend fun insertExercise(exercise: Exercise)
}