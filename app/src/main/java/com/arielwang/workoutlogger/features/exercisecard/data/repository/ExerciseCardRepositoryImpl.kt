package com.arielwang.workoutlogger.features.exercisecard.data.repository

import com.arielwang.workoutlogger.database.daos.ExerciseCardDao
import com.arielwang.workoutlogger.database.model.ExerciseCard
import com.arielwang.workoutlogger.features.exercisecard.domain.ExerciseCardRepository
import javax.inject.Inject

class ExerciseCardRepositoryImpl @Inject constructor(private val dao: ExerciseCardDao):
    ExerciseCardRepository {
    override suspend fun insertExerciseCard(exerciseCard: ExerciseCard) {
        dao.insertExerciseCard(exerciseCard)
    }

    override suspend fun updateExerciseCard(exerciseCard: ExerciseCard) {
        dao.updateExerciseCard(exerciseCard)
    }

    override suspend fun deleteExerciseCard(exerciseCard: ExerciseCard) {
        dao.deleteExerciseCard(exerciseCard)
    }
}