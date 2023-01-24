package com.arielwang.workoutlogger.features.workoutaddingflow.track.data.repository

import com.arielwang.workoutlogger.database.daos.WorkoutDao
import com.arielwang.workoutlogger.database.model.WorkoutAddingFlow
import com.arielwang.workoutlogger.features.workoutaddingflow.track.domain.repository.TrackRepository
import javax.inject.Inject

class TrackRepositoryImpl @Inject constructor(private val dao: WorkoutDao): TrackRepository {
    override suspend fun insertExercise(exercise: WorkoutAddingFlow) {
        dao.insertExercise(exercise)
    }
}