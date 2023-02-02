package com.arielwang.workoutlogger.features.exercisecard.ui

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import com.arielwang.workoutlogger.core.ui.screen.screenViewModel
import com.arielwang.workoutlogger.navigate.NavigationDestination
import com.arielwang.workoutlogger.navigate.destination.composable

object ExerciseCardDestination: NavigationDestination {

    private const val EXERCISEDETAIL_ROUTE = "ExerciseDetail"

    override fun route(): String = EXERCISEDETAIL_ROUTE
}

internal fun NavGraphBuilder.addExerciseDetailScreen() {
    with(ExerciseCardDestination) {
        composable(this) {
            screenViewModel<ExerciseCardViewModel>().let { viewModel ->
                val state: ExerciseCardView.State by viewModel.uiState.collectAsState()

                ExerciseCardScreen(state) { action -> viewModel.onUiAction(action) }
            }
        }
    }
}