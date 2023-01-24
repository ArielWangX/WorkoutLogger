package com.arielwang.workoutlogger.features.workoutaddingflow.exercise.domain

import com.arielwang.workoutlogger.database.model.ExerciseCard

interface ExerciseRepository {
    suspend fun getAllExerciseCards(): List<ExerciseCard>
}