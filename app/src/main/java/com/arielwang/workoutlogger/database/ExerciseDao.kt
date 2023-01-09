package com.arielwang.workoutlogger.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.arielwang.workoutlogger.database.model.Exercise

@Dao
interface ExerciseDao {
  @Query("SELECT * FROM exercise")
  suspend fun getAll(): List<Exercise>

  @Insert
  suspend fun insertExercise(exercise: Exercise)
}