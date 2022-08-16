package com.arielwang.workoutlogger.navigate

import android.net.Uri
import androidx.navigation.NavOptionsBuilder
import com.arielwang.workoutlogger.navigate.utils.NavigationStateManager
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class NavigatorImpl @Inject constructor(
  private val navigationStateManager: NavigationStateManager
) : Navigator, NavigationStateManager by navigationStateManager {
  private val navigationEvents = Channel<NavigatorEvent>()
  override val destinations = navigationEvents.receiveAsFlow()

  override fun navigateUp(): Boolean =
    navigationEvents.trySend(NavigatorEvent.NavigateUp).isSuccess

  override fun navigate(route: String, builder: NavOptionsBuilder.() -> Unit): Boolean =
    navigationEvents.trySend(NavigatorEvent.Directions(route, builder)).isSuccess

  override fun popToRoot(): Boolean =
    navigationEvents.trySend(NavigatorEvent.PopToRoot).isSuccess

  override fun navigateToRoot(route: String): Boolean =
    navigationEvents.trySend(NavigatorEvent.NavigateToRoot(route)).isSuccess

  override fun popTo(route: String, inclusive: Boolean): Boolean =
    navigationEvents.trySend(NavigatorEvent.PopTo(route, inclusive)).isSuccess

  override fun openDeepLink(uri: Uri) =
    navigationEvents.trySend(NavigatorEvent.DeepLink(uri)).isSuccess
}
