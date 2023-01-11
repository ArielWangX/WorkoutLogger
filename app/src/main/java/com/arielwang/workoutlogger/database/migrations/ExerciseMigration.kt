package com.arielwang.workoutlogger.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val migration_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE ExerciseTracking ADD COLUMN weight DOUBLE NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE ExerciseTracking ADD COLUMN reps INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE ExerciseTracking ADD COLUMN hours INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE ExerciseTracking ADD COLUMN mins INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE ExerciseTracking ADD COLUMN secs INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE ExerciseTracking ADD COLUMN comment STRING NOT NULL DEFAULT ''")
    }
}