package com.arielwang.workoutlogger.navigate.flow

import android.os.Bundle
import com.arielwang.workoutlogger.navigate.NavigationDestination

interface NavFlowDestination<T : NavFlow<*, *>> : NavigationDestination {
  fun createNavFlow(args: Bundle?): T
}
