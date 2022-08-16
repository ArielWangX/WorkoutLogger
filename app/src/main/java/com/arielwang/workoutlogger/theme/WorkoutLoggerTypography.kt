package com.arielwang.workoutlogger.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

internal val bigMoney = TextStyle(
  fontSize = 50.sp,
  fontWeight = FontWeight.W500,
  lineHeight = 64.sp,
  letterSpacing = 0.sp,
  fontStyle = FontStyle.Normal,
)

internal val header1 = TextStyle(
  fontSize = 40.sp,
  lineHeight = 48.sp,
  fontWeight = FontWeight.W700,
  letterSpacing = 0.sp,
  fontStyle = FontStyle.Normal,
)

internal val header2 = TextStyle(
  fontSize = 32.sp,
  fontWeight = FontWeight.W500,
  lineHeight = 40.sp,
  letterSpacing = 0.sp,
  fontStyle = FontStyle.Normal,
)

internal val header3 = TextStyle(
  fontSize = 24.sp,
  fontWeight = FontWeight.W500,
  lineHeight = 32.sp,
  letterSpacing = 0.01.sp,
  fontStyle = FontStyle.Normal,
)

internal val header4 = TextStyle(
  fontSize = 20.sp,
  fontWeight = FontWeight.W500,
  lineHeight = 28.sp,
  letterSpacing = 0.01.sp,
  fontStyle = FontStyle.Normal,
)

internal val input = TextStyle(
  fontSize = 20.sp,
  fontWeight = FontWeight.W400,
  lineHeight = 28.sp,
  letterSpacing = 0.01.sp,
  fontStyle = FontStyle.Normal,
)

internal val mainTitle = TextStyle(
  fontSize = 18.sp,
  fontWeight = FontWeight.W500,
  lineHeight = 24.sp,
  letterSpacing = 0.01.sp,
  fontStyle = FontStyle.Normal,
)

internal val mainBody = TextStyle(
  fontSize = 18.sp,
  fontWeight = FontWeight.W400,
  lineHeight = 24.sp,
  letterSpacing = 0.01.sp,
  fontStyle = FontStyle.Normal,
)

internal val smallTitle = TextStyle(
  fontSize = 16.sp,
  fontWeight = FontWeight.W500,
  lineHeight = 24.sp,
  letterSpacing = 0.01.sp,
  fontStyle = FontStyle.Normal,
)

internal val smallBody = TextStyle(
  fontSize = 16.sp,
  fontWeight = FontWeight.W400,
  lineHeight = 24.sp,
  letterSpacing = 0.01.sp,
  fontStyle = FontStyle.Normal,
)

internal val strongCaption = TextStyle(
  fontSize = 14.sp,
  fontWeight = FontWeight.W500,
  lineHeight = 20.sp,
  letterSpacing = 0.01.sp,
  fontStyle = FontStyle.Normal,
)

internal val caption = TextStyle(
  fontSize = 14.sp,
  fontWeight = FontWeight.W400,
  lineHeight = 20.sp,
  letterSpacing = 0.01.sp,
  fontStyle = FontStyle.Normal,
)

internal val identifier = TextStyle(
  fontSize = 12.sp,
  fontWeight = FontWeight.W500,
  lineHeight = 16.sp,
  letterSpacing = 0.12.sp,
  fontStyle = FontStyle.Normal,
)

internal val badge = TextStyle(
  fontSize = 8.sp,
  fontWeight = FontWeight.W700,
  lineHeight = 8.sp,
  letterSpacing = 0.01.sp,
  fontStyle = FontStyle.Normal,
)

val LocalWorkoutLoggerTypography = staticCompositionLocalOf {
  WorkoutLoggerTypography(
    bigMoney = TextStyle.Default,
    header1 = TextStyle.Default,
    header2 = TextStyle.Default,
    header3 = TextStyle.Default,
    header4 = TextStyle.Default,
    input = TextStyle.Default,
    mainTitle = TextStyle.Default,
    mainBody = TextStyle.Default,
    smallTitle = TextStyle.Default,
    smallBody = TextStyle.Default,
    strongCaption = TextStyle.Default,
    caption = TextStyle.Default,
    identifier = TextStyle.Default,
    badge = TextStyle.Default,
  )
}

@Immutable
data class WorkoutLoggerTypography(
  val bigMoney: TextStyle,
  val header1: TextStyle,
  val header2: TextStyle,
  val header3: TextStyle,
  val header4: TextStyle,
  val input: TextStyle,
  val mainTitle: TextStyle,
  val mainBody: TextStyle,
  val smallTitle: TextStyle,
  val smallBody: TextStyle,
  val strongCaption: TextStyle,
  val caption: TextStyle,
  val identifier: TextStyle,
  val badge: TextStyle,
)
