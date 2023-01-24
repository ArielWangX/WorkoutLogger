package com.arielwang.workoutlogger.database

import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import com.arielwang.workoutlogger.database.converters.ExerciseTypeConverter
import com.arielwang.workoutlogger.database.daos.ExerciseCardDao
import com.arielwang.workoutlogger.database.daos.WorkoutDao
import com.arielwang.workoutlogger.database.model.ExerciseCard
import com.arielwang.workoutlogger.database.model.WorkoutAddingFlow

const val DATABASE_VERSION = 4
@Database(
  entities = [WorkoutAddingFlow::class, ExerciseCard::class],
  version = DATABASE_VERSION,
  autoMigrations = [
    AutoMigration (from = 3, to = 4, spec = AppDatabase.MyAutoMigration::class)
  ]
)
@TypeConverters(ExerciseTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
  @RenameTable(fromTableName = "ExerciseFlow", toTableName = "WorkoutAddingFlow")
  class MyAutoMigration : AutoMigrationSpec

  abstract fun exerciseDao(): WorkoutDao
  abstract fun exerciseCardDao(): ExerciseCardDao
}