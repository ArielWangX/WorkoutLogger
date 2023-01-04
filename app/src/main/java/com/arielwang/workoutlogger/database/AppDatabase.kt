package com.arielwang.workoutlogger.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arielwang.workoutlogger.database.model.Exercise

@Database(entities = [Exercise::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
  abstract fun exerciseDao(): ExerciseDao
}