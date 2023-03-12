package com.arielwang.workoutlogger.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColors(
  primary = Orange500,  // Theme color
  primaryVariant = Green200,
  onPrimary = Grey300,  // Container color
  secondary = Grey800,  // Icon and Text color
  secondaryVariant = Grey600,
//  onSecondary = Grey400,
  surface= Grey50,  // Card
  background= Color.White,
  onBackground = Blue500,  // highlight
  onSurface= Green100,
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
