package com.arielwang.workoutlogger.navigate

import android.net.Uri
import androidx.navigation.NavOptionsBuilder

sealed class NavigatorEvent {
  object PopToRoot : NavigatorEvent()
  object NavigateUp : NavigatorEvent()
  data class PopTo(val route: String, val inclusive: Boolean) : NavigatorEvent()
  data class NavigateToRoot(val route: String) : NavigatorEvent()

  data class Directions(
    val destination: String,
    val builder: NavOptionsBuilder.() -> Unit
  ) : NavigatorEvent()

  data class DeepLink(val uri: Uri) : NavigatorEvent()
}
