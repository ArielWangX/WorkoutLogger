package com.arielwang.workoutlogger.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

val workoutLoggerTypography = WorkoutLoggerTypography(
  bigMoney = bigMoney,
  header1 = header1,
  header2 = header2,
  header3 = header3,
  header4 = header4,
  input = input,
  mainTitle = mainTitle,
  mainBody = mainBody,
  smallTitle = smallTitle,
  smallBody = smallBody,
  strongCaption = strongCaption,
  caption = caption,
  identifier = identifier,
  badge = badge,
)

@Composable
fun WorkoutLoggerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
  val colors = if (darkTheme) workoutLoggerDarkColorPalette else workoutLoggerLightColorPalette

  CompositionLocalProvider(
    LocalWorkoutLoggerTypography provides workoutLoggerTypography,
    LocalWorkoutLoggerColorPalette provides colors,
    LocalIndication provides rememberRipple(),
    content = content
  )
}

object WorkoutLoggerTheme {
  val typography: WorkoutLoggerTypography
    @Composable
    get() = LocalWorkoutLoggerTypography.current

  val colors: WorkoutLoggerColorPalette
    @Composable
    get() = LocalWorkoutLoggerColorPalette.current
}
