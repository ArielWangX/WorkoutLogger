package com.arielwang.workoutlogger.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.arielwang.workoutlogger.database.model.WorkoutData
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
  @Query("SELECT * FROM WorkoutData")
  suspend fun getAllWorkoutData(): List<WorkoutData>

  @Query("SELECT * FROM WorkoutData")
  fun getAllWorkoutDataFlow(): Flow<List<WorkoutData>>

  @Insert
  suspend fun insertWorkout(exercise: WorkoutData)
}