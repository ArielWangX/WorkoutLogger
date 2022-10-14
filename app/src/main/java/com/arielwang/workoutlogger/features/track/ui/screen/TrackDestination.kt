package com.arielwang.workoutlogger.features.track.ui.screen

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import com.arielwang.workoutlogger.core.ui.screen.screenViewModel
import com.arielwang.workoutlogger.features.exercise.ui.screen.ExerciseView
import com.arielwang.workoutlogger.navigate.NavigationDestination
import com.arielwang.workoutlogger.navigate.destination.composable

object TrackDestination: NavigationDestination {
    private const val TRACK_ROUTE = "Track"

    override fun route(): String = TRACK_ROUTE
}

internal fun NavGraphBuilder.addTrackScreen() {
    with(TrackDestination) {
        composable(this) {
            screenViewModel<TrackViewModel>().let { viewModel ->
                val state: TrackView.State by viewModel.uiState.collectAsState()

                TrackScreen(state) { action -> viewModel.onUiAction(action)}
            }
        }
    }
}