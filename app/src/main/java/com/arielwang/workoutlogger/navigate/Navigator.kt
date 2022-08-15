package com.arielwang.workoutlogger.navigate

import android.content.Intent
import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.arielwang.workoutlogger.navigate.utils.NavigationStateManager
import com.arielwang.workoutlogger.navigate.utils.navigateToRoot
import com.arielwang.workoutlogger.navigate.utils.popTo
import com.arielwang.workoutlogger.navigate.utils.popToRoot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

interface Navigator : NavigationStateManager {
  val destinations: Flow<NavigatorEvent>

  fun navigateUp(): Boolean

  fun navigate(
    route: String,
    builder: NavOptionsBuilder.() -> Unit = { launchSingleTop = true },
  ): Boolean

  fun popToRoot(): Boolean

  fun navigateToRoot(route: String): Boolean

  fun popTo(route: String, inclusive: Boolean = false): Boolean

  fun openDeepLink(uri: Uri): Boolean
}

fun Navigator.setup(scope: CoroutineScope, navController: NavController) {
  attachToNavController(navController)
  destinations.onEach { event ->
    when (event) {
      is NavigatorEvent.NavigateUp -> navController.navigateUp()
      is NavigatorEvent.Directions -> navController.navigate(event.destination, event.builder)
      NavigatorEvent.PopToRoot -> navController.popToRoot()
      is NavigatorEvent.NavigateToRoot -> navController.navigateToRoot(
        event.route,
      )
      is NavigatorEvent.PopTo -> navController.popTo(
        event.route,
        event.inclusive
      )
      is NavigatorEvent.DeepLink -> navController.handleDeepLink(
        Intent(Intent.ACTION_VIEW).apply {
          data = event.uri
        }
      )
    }
  }.launchIn(scope)
}