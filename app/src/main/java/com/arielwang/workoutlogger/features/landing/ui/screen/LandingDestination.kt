package com.arielwang.workoutlogger.features.landing.ui.screen

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import com.arielwang.workoutlogger.core.ui.screen.screenViewModel
import com.arielwang.workoutlogger.navigate.NavigationDestination
import com.arielwang.workoutlogger.navigate.destination.composable

object LandingDestination : NavigationDestination {

  private const val LANDING_ROUTE = "landing"

  override fun route(): String = LANDING_ROUTE
}

internal fun NavGraphBuilder.addLandingScreen() {
  with(LandingDestination) {
    composable(this) {
      screenViewModel<LandingViewModel>().let { viewModel ->
        val state: LandingView.State by viewModel.uiState.collectAsState()

        LandingScreen(state) { action -> viewModel.onUiAction(action) }
      }
    }
  }
}