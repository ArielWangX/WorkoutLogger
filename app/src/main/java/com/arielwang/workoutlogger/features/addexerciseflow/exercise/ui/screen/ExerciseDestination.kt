package com.arielwang.workoutlogger.features.addexerciseflow.exercise.ui.screen

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import com.arielwang.workoutlogger.core.ui.screen.screenViewModel
import com.arielwang.workoutlogger.navigate.NavigationDestination
import com.arielwang.workoutlogger.navigate.destination.composable


object ExerciseDestination: NavigationDestination {
    private const val EXERCISE_ROUTE = "Exercise"

    override fun route():String = EXERCISE_ROUTE
}

internal fun NavGraphBuilder.addExerciseScreen() {
    with(ExerciseDestination) {
        composable(this) {
            screenViewModel<ExerciseViewModel>().let { viewModel ->
                val state: ExerciseView.State by viewModel.uiState.collectAsState()

                ExerciseScreen(state) { action -> viewModel.onUiAction(action)}
            }
        }
    }
}