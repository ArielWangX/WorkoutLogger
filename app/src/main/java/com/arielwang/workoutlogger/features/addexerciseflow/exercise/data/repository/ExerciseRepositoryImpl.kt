package com.arielwang.workoutlogger.features.addexerciseflow.exercise.data.repository

import com.arielwang.workoutlogger.database.daos.ExerciseDao
import com.arielwang.workoutlogger.features.addexerciseflow.exercise.domain.repository.ExerciseRepository
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(private val dao: ExerciseDao): ExerciseRepository {}