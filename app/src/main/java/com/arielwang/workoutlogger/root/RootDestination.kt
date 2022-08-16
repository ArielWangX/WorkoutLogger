package com.arielwang.workoutlogger.root

import com.arielwang.workoutlogger.navigate.NavigationDestination

object RootDestination : NavigationDestination {

  private const val ROOT_ROUTE = "root"

  override fun route(): String = ROOT_ROUTE
}
