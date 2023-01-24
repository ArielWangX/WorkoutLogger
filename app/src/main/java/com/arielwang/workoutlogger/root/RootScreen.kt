package com.arielwang.workoutlogger.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.arielwang.workoutlogger.features.workoutaddingflow.exercise.ui.screen.addExerciseScreen
import com.arielwang.workoutlogger.features.home.ui.screen.HomeDestination
import com.arielwang.workoutlogger.features.home.ui.screen.addHomeScreen
import com.arielwang.workoutlogger.features.landing.ui.screen.addLandingScreen
import com.arielwang.workoutlogger.features.workoutaddingflow.track.ui.screen.addTrackScreen
import com.arielwang.workoutlogger.features.exercisecard.ui.addExerciseDetailScreen
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun RootScreen(
  bottomSheetNavigator: BottomSheetNavigator = rememberModalBottomSheetNavigator(),
  navController: NavHostController = rememberNavController(bottomSheetNavigator),
) {

  NavHost(
    navController,
    route = RootDestination.route(),
    startDestination = HomeDestination.route()
  ) {
    addHomeScreen()
    addLandingScreen()
    addExerciseScreen()
    addExerciseDetailScreen()
    addTrackScreen()
  }
}