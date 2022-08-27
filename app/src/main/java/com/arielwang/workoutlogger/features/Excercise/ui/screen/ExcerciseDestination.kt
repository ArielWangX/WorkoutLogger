package com.arielwang.workoutlogger.features.Excercise.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import com.arielwang.workoutlogger.core.ui.screen.screenViewModel
import com.arielwang.workoutlogger.navigate.NavigationDestination
import com.arielwang.workoutlogger.navigate.destination.composable


object ExcerciseDestination: NavigationDestination {
    private const val EXCERCISE_ROUTE = "Excercise"

    override fun route():String = EXCERCISE_ROUTE
}

internal fun NavGraphBuilder.addExcerciseScreen() {
    with(ExcerciseDestination) {
        composable(this) {
            screenViewModel<ExcerciseViewModel>().let { viewModel ->
                val state: ExcerciseView.State by viewModel.uiState.collectAsState()

                ExcerciseScreen() // {action -> viewModel.onUiAction}
            }
        }
    }
}