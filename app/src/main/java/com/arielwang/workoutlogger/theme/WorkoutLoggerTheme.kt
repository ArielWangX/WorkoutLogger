package com.arielwang.workoutlogger.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColors(
  primary = Blue700,  // button color
  primaryVariant = Green700,  // selected color
  onPrimary = Color.White,
  secondary = Black500, // text color
  secondaryVariant = Red900,
  onSecondary = LightGrey700,   //border color
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
