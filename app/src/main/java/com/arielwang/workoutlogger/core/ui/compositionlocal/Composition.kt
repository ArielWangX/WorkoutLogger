package com.arielwang.workoutlogger.core.ui.compositionlocal

import androidx.compose.runtime.compositionLocalOf
import timber.log.Timber

val LocalWindowSize = compositionLocalOf { WindowSize.Compact }

const val FONT_SCALE_THRESHOLD = 1.3f

fun stackVertically(fontScale: Float, windowSize: WindowSize): Boolean {
  Timber.d("windowSize: $windowSize, fontScale:$fontScale")
  return fontScale >= FONT_SCALE_THRESHOLD && windowSize.ordinal <= WindowSize.Small.ordinal
}
