package com.arielwang.workoutlogger.features.addexerciseflow.track.data.repository

import com.arielwang.workoutlogger.database.daos.ExerciseDao
import com.arielwang.workoutlogger.database.model.ExerciseFlow
import com.arielwang.workoutlogger.features.addexerciseflow.track.domain.repository.TrackRepository
import javax.inject.Inject

class TrackRepositoryImpl @Inject constructor(private val dao: ExerciseDao): TrackRepository {
    override suspend fun insertExercise(exercise: ExerciseFlow) {
        dao.insertExercise(exercise)
    }
}