package com.arielwang.workoutlogger.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exercise(
  @PrimaryKey(autoGenerate = true) val id: Int = 0,
  @ColumnInfo(name = "type") val type: String
)