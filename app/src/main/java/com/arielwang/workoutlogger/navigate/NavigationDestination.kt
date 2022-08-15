package com.arielwang.workoutlogger.navigate

import androidx.navigation.NamedNavArgument
import java.io.Serializable

interface NavigationDestination : Serializable {

  fun route(): String

  /**
   * A human readable name for a screen.
   * This is also used for analytics purposes.
   */
  fun screenId(): String = route()

  val arguments: List<NamedNavArgument>
    get() = emptyList()
}