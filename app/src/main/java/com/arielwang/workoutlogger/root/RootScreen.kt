package com.arielwang.workoutlogger.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.arielwang.workoutlogger.features.landing.ui.screen.LandingDestination
import com.arielwang.workoutlogger.features.landing.ui.screen.addLandingScreen
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
    startDestination = LandingDestination.route()
  ) {
    addLandingScreen()
  }
}