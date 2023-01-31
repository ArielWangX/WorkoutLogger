package com.arielwang.workoutlogger.features.workoutaddingflow.exercise.data.repository

import com.arielwang.workoutlogger.database.daos.ExerciseCardDao
import com.arielwang.workoutlogger.database.model.ExerciseCard
import com.arielwang.workoutlogger.features.workoutaddingflow.exercise.domain.ExerciseRepository
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(private val dao: ExerciseCardDao):
    ExerciseRepository {
    override suspend fun getAllExerciseCards(): List<ExerciseCard> {
        return dao.getAllExerciseCards()
    }
}