package com.arielwang.workoutlogger.features.workoutaddingflow.exercise.domain

import com.arielwang.workoutlogger.database.model.ExerciseCard
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    suspend fun getAllExerciseCards(): List<ExerciseCard>

    fun getAllExerciseCardsFlow(): Flow<List<ExerciseCard>>
}