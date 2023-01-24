package com.arielwang.workoutlogger.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.arielwang.workoutlogger.database.converters.ExerciseTypeConverter

@Entity
data class WorkoutAddingFlow(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    @TypeConverters(ExerciseTypeConverter::class)
    @ColumnInfo(name = "type") val type: List<String> = emptyList(),

    @ColumnInfo(name = "weight") val weight: Int = 0,
    @ColumnInfo(name = "reps") val reps: Int = 0,
    @ColumnInfo(name = "hours") val hours: Int = 0,
    @ColumnInfo(name = "mins") val mins: Int = 0,
    @ColumnInfo(name = "secs") val secs: Int = 0,
    @ColumnInfo(name = "comment") val comment: String = ""
)
