@file:OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalMaterialNavigationApi::class)
package com.arielwang.workoutlogger.navigate.destination

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import androidx.navigation.get
import androidx.navigation.navArgument
import com.arielwang.workoutlogger.navigate.flow.NavFlowDestination
import com.arielwang.workoutlogger.navigate.NavigationDestination
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import androidx.navigation.compose.composable as navigationComposable
import com.google.accompanist.navigation.material.bottomSheet as navigationBottomSheet

internal const val ARG_ANALYTICS_NAME = "analyticsName"

internal val cacheList = mutableListOf<NavFlowDestination<*>>()

fun NavGraphBuilder.composable(
  destination: NavigationDestination,
  deepLinks: List<NavDeepLink> = emptyList(),
  content: @Composable (NavBackStackEntry) -> Unit,
) {
  val route = destination.route()
  val analyticsName = destination.screenId()
  val arguments = destination.arguments
  if (destination is NavFlowDestination<*>) {
    storeCacheDestination(destination)
  }
  navigationComposable(
    route,
    addAnalyticsToArguments(analyticsName, arguments),
    deepLinks,
    content
  )
}

private fun storeCacheDestination(destination: NavFlowDestination<*>) {
  cacheList.add(destination)
}

/**
 * Exactly the same as [androidx.navigation.compose.composable]
 * except this one lets you set a [analyticsName]
 */
fun NavGraphBuilder.composable(
  route: String,
  analyticsName: String = route,
  arguments: List<NamedNavArgument> = emptyList(),
  deepLinks: List<NavDeepLink> = emptyList(),
  content: @Composable (NavBackStackEntry) -> Unit,
) {
  addDestination(
    ComposeNavigator.Destination(provider[ComposeNavigator::class], content).apply {
      this.route = route
      addAnalyticsArgument(analyticsName)
      arguments.forEach { (argumentName, argument) ->
        addArgument(argumentName, argument)
      }
      deepLinks.forEach { deepLink ->
        addDeepLink(deepLink)
      }
    }
  )
}

/**
 * Exactly the same as [androidx.navigation.compose.dialog]
 * except this one lets you set a [analyticsName]
 */
fun NavGraphBuilder.dialog(
  route: String,
  analyticsName: String = route,
  arguments: List<NamedNavArgument> = emptyList(),
  deepLinks: List<NavDeepLink> = emptyList(),
  dialogProperties: DialogProperties = DialogProperties(),
  content: @Composable (NavBackStackEntry) -> Unit,
) {
  addDestination(
    DialogNavigator.Destination(
      provider[DialogNavigator::class],
      dialogProperties,
      content
    ).apply {
      this.route = route
      addAnalyticsArgument(analyticsName)
      arguments.forEach { (argumentName, argument) ->
        addArgument(argumentName, argument)
      }
      deepLinks.forEach { deepLink ->
        addDeepLink(deepLink)
      }
    }
  )
}

fun NavGraphBuilder.bottomSheet(
  destination: NavigationDestination,
  deepLinks: List<NavDeepLink> = emptyList(),
  content: @Composable ColumnScope.(NavBackStackEntry) -> Unit,
) {
  val route = destination.route()
  val analyticsName = destination.screenId()
  val arguments = destination.arguments
  if (destination is NavFlowDestination<*>) {
    storeCacheDestination(destination)
  }
  navigationBottomSheet(
    route,
    addAnalyticsToArguments(analyticsName, arguments),
    deepLinks,
    content
  )
}

/**
 * Exactly the same as [com.google.accompanist.navigation.material.bottomSheet]
 * except this one lets you set a [analyticsName]
 */
fun NavGraphBuilder.bottomSheet(
  route: String,
  analyticsName: String = route,
  arguments: List<NamedNavArgument> = emptyList(),
  deepLinks: List<NavDeepLink> = emptyList(),
  content: @Composable ColumnScope.(backstackEntry: NavBackStackEntry) -> Unit,
) {
  addDestination(
    BottomSheetNavigator.Destination(
      provider[BottomSheetNavigator::class],
      content,
    ).apply {
      addAnalyticsArgument(analyticsName)
      this.route = route
      arguments.forEach { (argumentName, argument) ->
        addArgument(argumentName, argument)
      }
      deepLinks.forEach { deepLink ->
        addDeepLink(deepLink)
      }
    }
  )
}

private fun NavDestination.addAnalyticsArgument(analyticsName: String) {
  addArgument(
    ARG_ANALYTICS_NAME,
    NavArgument
      .Builder()
      .setType(NavType.StringType)
      .setIsNullable(false)
      .setDefaultValue(analyticsName)
      .build()
  )
}

private fun addAnalyticsToArguments(
  analyticsName: String,
  arguments: List<NamedNavArgument>,
): List<NamedNavArgument> {
  return arguments.toMutableList()
    .apply {
      add(
        navArgument(ARG_ANALYTICS_NAME) {
          type = NavType.StringType
          nullable = false
          defaultValue = analyticsName
        }
      )
    }.toList()
}
