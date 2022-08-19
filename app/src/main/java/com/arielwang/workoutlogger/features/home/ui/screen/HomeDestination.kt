package com.arielwang.workoutlogger.features.home.ui.screen

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import com.arielwang.workoutlogger.core.ui.screen.screenViewModel
import com.arielwang.workoutlogger.navigate.NavigationDestination
import com.arielwang.workoutlogger.navigate.destination.composable

object HomeDestination: NavigationDestination {

    private const val HOME_ROUTE = "Home"

    override fun route(): String = HOME_ROUTE
}

internal fun NavGraphBuilder.addHomeScreen() {
    with(HomeDestination) {
        composable(this) {
            screenViewModel<HomeViewModel>().let { viewModel ->
                val state: HomeView.State by viewModel.uiState.collectAsState()

                HomeScreen(state) { action -> viewModel.onUiAction(action)}
            }
        }
    }
}