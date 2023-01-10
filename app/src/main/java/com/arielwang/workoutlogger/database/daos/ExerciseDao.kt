package com.arielwang.workoutlogger.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.arielwang.workoutlogger.database.model.ExerciseFlow

@Dao
interface ExerciseDao {
  @Query("SELECT * FROM ExerciseFlow")
  suspend fun getAll(): List<ExerciseFlow>

  @Insert
  suspend fun insertExercise(exercise: ExerciseFlow)
}