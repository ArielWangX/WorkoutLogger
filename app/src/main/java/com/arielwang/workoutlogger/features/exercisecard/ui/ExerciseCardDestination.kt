package com.arielwang.workoutlogger.features.exercisecard.ui

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import com.arielwang.workoutlogger.core.ui.screen.screenViewModel
import com.arielwang.workoutlogger.navigate.NavigationDestination
import com.arielwang.workoutlogger.navigate.destination.composable

object ExerciseDetailDestination: NavigationDestination {

    private const val EXERCISEDETAIL_ROUTE = "ExerciseDetail"

    override fun route(): String = EXERCISEDETAIL_ROUTE
}

internal fun NavGraphBuilder.addExerciseDetailScreen() {
    with(ExerciseDetailDestination) {
        composable(this) {
            screenViewModel<ExerciseCardViewModel>().let { viewModel ->
                val state: ExerciseCardView.State by viewModel.uiState.collectAsState()

                ExerciseDetailScreen(state) { action -> viewModel.onUiAction(action) }
            }
        }
    }
}