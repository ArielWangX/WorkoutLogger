package com.arielwang.workoutlogger.features.home.data.repository

import com.arielwang.workoutlogger.database.daos.WorkoutDao
import com.arielwang.workoutlogger.database.model.WorkoutData
import com.arielwang.workoutlogger.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val dao: WorkoutDao) : HomeRepository {
    override suspend fun getAllWorkoutData(): List<WorkoutData> {
        return dao.getAllWorkoutData()
    }

    override fun getAllWorkoutDataFlow(): Flow<List<WorkoutData>> {
        return dao.getAllWorkoutDataFlow()
    }
}