package com.arielwang.workoutlogger.navigate.utils

import android.os.Bundle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.arielwang.workoutlogger.navigate.destination.ARG_ANALYTICS_NAME
import java.lang.ref.WeakReference
import javax.inject.Inject

internal class NavigationStateManagerImpl @Inject constructor() : NavigationStateManager,
  NavController.OnDestinationChangedListener {

  private var currentScreen: String? = null

  private var lastScreen: String? = null
  private var currentNavController = WeakReference<NavController?>(null)

  override fun attachToNavController(navController: NavController) {
    currentNavController = WeakReference(navController)
    navController.addOnDestinationChangedListener(this)
  }

  override fun getCurrentScreenId(): String? =
    currentScreen

  override fun getPreviousScreenId(): String? =
    lastScreen

  override fun onDestinationChanged(
    controller: NavController,
    destination: NavDestination,
    arguments: Bundle?,
  ) {
    destination.arguments[ARG_ANALYTICS_NAME]?.let {
      val newScreen = it.defaultValue as String
      lastScreen = currentScreen
      currentScreen = newScreen
    }
  }

  override fun getCurrentBackStackSavedState() =
    currentNavController.get()?.currentBackStackEntry?.savedStateHandle

  override fun getPreviousBackStackSavedState() =
    currentNavController.get()?.previousBackStackEntry?.savedStateHandle

  private fun NavController.getBackStackEntryOrNull(route: String): NavBackStackEntry? =
    backQueue.lastOrNull { entry ->
      entry.destination.route == route
    }
}
