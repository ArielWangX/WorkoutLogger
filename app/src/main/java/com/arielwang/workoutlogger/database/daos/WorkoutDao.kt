package com.arielwang.workoutlogger.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.arielwang.workoutlogger.database.model.WorkoutData

@Dao
interface WorkoutDao {
  @Query("SELECT * FROM WorkoutData")
  suspend fun getAllWorkoutData(): List<WorkoutData>

  @Insert
  suspend fun insertWorkout(exercise: WorkoutData)
}