package com.arielwang.workoutlogger.database

import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import com.arielwang.workoutlogger.database.converters.ExerciseTypeConverter
import com.arielwang.workoutlogger.database.daos.ExerciseCardDao
import com.arielwang.workoutlogger.database.daos.WorkoutDao
import com.arielwang.workoutlogger.database.model.ExerciseCard
import com.arielwang.workoutlogger.database.model.WorkoutData
import javax.inject.Singleton

const val DATABASE_VERSION = 5
@Database(
  entities = [WorkoutData::class, ExerciseCard::class],
  version = DATABASE_VERSION,
  autoMigrations = [
    AutoMigration (from = 4, to = 5, spec = AppDatabase.MyAutoMigration::class)
  ]
)
@TypeConverters(ExerciseTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
  @RenameTable(fromTableName = "ExerciseFlow", toTableName = "WorkoutAddingFlow")
  @RenameTable(fromTableName = "WorkoutAddingFlow", toTableName = "WorkoutData")
  class MyAutoMigration : AutoMigrationSpec

  abstract fun workoutDao(): WorkoutDao
  abstract fun exerciseCardDao(): ExerciseCardDao
}