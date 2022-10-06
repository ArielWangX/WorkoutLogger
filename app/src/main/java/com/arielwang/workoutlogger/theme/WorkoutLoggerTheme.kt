package com.arielwang.workoutlogger.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColors(
  primary = Blue700,  // Theme color
  primaryVariant = LightGrey700,
  onPrimary = Color.White,  // text color
  secondary = Green700, // accent color
  secondaryVariant = Red900,
  onSecondary = Black500,
  error = Red800
)

private val DarkColors = darkColors(
  primary = Red300,
  primaryVariant = Red700,
  onPrimary = Color.Black,
  secondary = Color.White,
  onSecondary = Color.Black,
  error = Red200
)

@Composable
fun WorkoutLoggerTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  MaterialTheme (
    colors = if (darkTheme) DarkColors else LightColors,
    typography = WorkoutLoggerTypography,
    shapes = WorkoutLoggerShapes,
    content = content
  )
}
