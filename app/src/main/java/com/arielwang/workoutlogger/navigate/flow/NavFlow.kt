package com.arielwang.workoutlogger.navigate.flow

import android.os.Parcelable
import com.arielwang.workoutlogger.navigate.Navigator
import kotlinx.coroutines.flow.StateFlow

interface NavFlow<T, S> : Parcelable {

  val navFlowScope: NavFlowDestination<*>

  val state: StateFlow<T>

  fun update(newState: T)

  fun endStep(navigator: Navigator, result: S)
}
