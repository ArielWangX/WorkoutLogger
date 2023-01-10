package com.arielwang.workoutlogger.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ExerciseTypeConverter {
    @TypeConverter
    fun fromExercise(list: List<String>): String = Gson().toJson(list)

    @TypeConverter
    fun toExercise(json: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type

        return try {
            Gson().fromJson(json, listType)
        } catch (error: Throwable) {
            emptyList()
        }
    }
}