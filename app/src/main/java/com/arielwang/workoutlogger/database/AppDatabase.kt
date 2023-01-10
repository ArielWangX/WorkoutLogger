package com.arielwang.workoutlogger.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arielwang.workoutlogger.database.converters.ExerciseTypeConverter
import com.arielwang.workoutlogger.database.daos.ExerciseDao
import com.arielwang.workoutlogger.database.model.ExerciseFlow

const val DATABASE_VERSION = 2

@Database(
  entities = [ExerciseFlow::class],
  version = DATABASE_VERSION
)
@TypeConverters(ExerciseTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
  abstract fun exerciseDao(): ExerciseDao
}