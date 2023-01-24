package com.arielwang.workoutlogger.features.exercisecard.domain

import com.arielwang.workoutlogger.database.model.ExerciseCard

interface ExerciseCardRepository {
    suspend fun insertExerciseCard(exerciseCard: ExerciseCard)

    suspend fun updateExerciseCard(exerciseCard: ExerciseCard)

    suspend fun deleteExerciseCard(exerciseCard: ExerciseCard)
}