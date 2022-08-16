package com.arielwang.workoutlogger.root

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import com.arielwang.workoutlogger.navigate.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
internal class RootViewModel @Inject constructor(
  private val rootNavigator: Navigator
) : ViewModel(), DefaultLifecycleObserver, Navigator by rootNavigator
