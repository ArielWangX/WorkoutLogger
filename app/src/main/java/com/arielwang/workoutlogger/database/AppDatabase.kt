package com.arielwang.workoutlogger.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arielwang.workoutlogger.database.converters.ExerciseTypeConverter
import com.arielwang.workoutlogger.database.daos.ExerciseCardDao
import com.arielwang.workoutlogger.database.daos.ExerciseDao
import com.arielwang.workoutlogger.database.model.ExerciseCard
import com.arielwang.workoutlogger.database.model.ExerciseFlow

const val DATABASE_VERSION = 3
@Database(
  entities = [ExerciseFlow::class, ExerciseCard::class],
  version = DATABASE_VERSION,
  autoMigrations = [
    AutoMigration (from = 2, to = 3)
  ]
)
@TypeConverters(ExerciseTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
  abstract fun exerciseDao(): ExerciseDao
  abstract fun exerciseCardDao(): ExerciseCardDao
}