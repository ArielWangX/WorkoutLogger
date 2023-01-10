package com.arielwang.workoutlogger.database.di

import android.content.Context
import androidx.room.Room
import com.arielwang.workoutlogger.database.AppDatabase
import com.arielwang.workoutlogger.database.daos.ExerciseDao
import com.arielwang.workoutlogger.database.migrations.migration_1_2
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DatabaseModule {
  companion object {
    @Provides
    fun provideDatabase(
      @ApplicationContext
      context: Context
    ): AppDatabase {
      return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME,
      )
        .addMigrations(migration_1_2)
        .build()
    }

    @Provides
    fun provideExerciseDao(
      database: AppDatabase
    ): ExerciseDao = database.exerciseDao()

    private const val DATABASE_NAME = "workout-database"
  }

}