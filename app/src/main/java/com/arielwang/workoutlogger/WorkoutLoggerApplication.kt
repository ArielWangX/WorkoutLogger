package com.arielwang.workoutlogger

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class WorkoutLoggerApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    Timber.plant()
  }

}