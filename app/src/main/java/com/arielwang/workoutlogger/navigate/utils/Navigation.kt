package com.arielwang.workoutlogger.navigate.utils

import androidx.navigation.NavController

fun NavController.navigateToRoot(route: String) {
  navigate(route) {
    popUpTo(graph.id)
    launchSingleTop = true
  }
}

fun NavController.popToRoot() {
  popBackStack(graph.id, inclusive = false)
}

fun NavController.popTo(route: String, inclusive: Boolean = false) {
  popBackStack(route, inclusive = inclusive)
}
