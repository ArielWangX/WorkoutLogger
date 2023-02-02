package com.arielwang.workoutlogger.database.daos

import androidx.room.*
import com.arielwang.workoutlogger.database.model.ExerciseCard

@Dao
interface ExerciseCardDao {
    @Query("SELECT * FROM ExerciseCard")
    suspend fun getAllExerciseCards(): List<ExerciseCard>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseCard(exerciseCard: ExerciseCard)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateExerciseCard(exerciseCard: ExerciseCard)

    @Delete
    suspend fun deleteExerciseCard(exerciseCard: ExerciseCard)
}