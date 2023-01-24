package com.arielwang.workoutlogger.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.arielwang.workoutlogger.database.model.WorkoutAddingFlow

@Dao
interface WorkoutDao {
  @Query("SELECT * FROM WorkoutAddingFlow")
  suspend fun getAllExercise(): List<WorkoutAddingFlow>

  @Insert
  suspend fun insertExercise(exercise: WorkoutAddingFlow)
}