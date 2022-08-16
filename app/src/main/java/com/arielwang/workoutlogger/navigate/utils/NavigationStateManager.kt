package com.arielwang.workoutlogger.navigate.utils

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController

interface NavigationStateManager {

  fun attachToNavController(navController: NavController)

  fun getCurrentScreenId(): String?

  fun getPreviousScreenId(): String?

  fun getCurrentBackStackSavedState(): SavedStateHandle?

  fun getPreviousBackStackSavedState(): SavedStateHandle?
}
