package com.arielwang.workoutlogger.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class WorkoutLoggerColorPalette(

  @Deprecated("Move to the appropriate semantic named color.")
  val gray20: Color,
  @Deprecated("Move to the appropriate semantic named color.")
  val gray60: Color,
  @Deprecated("Move to the appropriate semantic named color.")
  val gray85: Color,
  @Deprecated("Move to the appropriate semantic named color.")
  val white: Color,

  val tint: Color,
  val green: Color,
  val verificationTint: Color,
  val error: Color,
  val warning: Color,
  val bitcoin: Color,
  val lending: Color,
  val investing: Color,

  val behindBackground: Color,
  val background: Color,
  val secondaryBackground: Color,
  val tertiaryBackground: Color,
  val placeholderBackground: Color,
  val elevatedBackground: Color,
  val secondaryElevatedBackground: Color,
  val statusBarBackground: Color,

  val cardTabBackground: Color,

  val label: Color,
  val secondaryLabel: Color,
  val tertiaryLabel: Color,
  val placeholderLabel: Color,
  val disabledLabel: Color,
  val activeLinkForeground: Color,
  val linkForeground: Color,

  val cursor: Color,
  val clearButtonTint: Color,

  val primaryButtonBackground: Color,
  val primaryButtonTint: Color,
  val primaryButtonTintInverted: Color,
  val secondaryButtonBackground: Color,
  val secondaryButtonTint: Color,
  val tertiaryButtonTint: Color,
  val outlineButtonBorder: Color,
  val outlineButtonSelectedBorder: Color,
  val segmentedControlForeground: Color,
  val segmentedControlBackground: Color,

  val switchThumbUnchecked: Color,
  val switchTrackUnchecked: Color,

  val icon: Color,
  val secondaryIcon: Color,
  val tertiaryIcon: Color,
  val placeholderIcon: Color,
  val disabledIcon: Color,
  val chevron: Color,
  val dragHandle: Color,

  val hairline: Color,
  val outline: Color,
  val unselectedPasscodeDot: Color,
  val widgetForeground: Color,
  val keyboard: Color,

  val tabBarShadow: Color,
  val bottomTabBarShadow: Color,

  val paymentPadBackground: Color,
  val paymentPadButtonBackground: Color,
  val paymentPadGhostedTextColor: Color,
  val paymentPadKeyboard: Color,

  val bitcoinPaymentPadBackground: Color,
  val bitcoinPaymentPadButtonBackground: Color,

  val pageControlUnselected: Color,
  val pageControlSelected: Color,
  val baselineStroke: Color,
  val grayChartStroke: Color,
  val scrubbingChartStroke: Color,
  val investingCellAccessoryLight: Color,
  val investingCellAccessoryDark: Color,
  val investingSelectableLabelOutline: Color,
  val customOrderBackgroundColor: Color,
  val customOrderSelectedLineColor: Color,
  val customOrderTooltipBackgroundColor: Color,
  val customOrderWidgetButtonBackground: Color,

  val scrollBar: Color,
  val scrollHint: Color,

  val captureLetterbox: Color,
  val captureTextColor: Color,
  val captureButtonForeground: Color,

  val cardCustomizationStroke: Color,
  val cardCustomizationStrokeOutsideCard: Color,

  val notificationBadge: Color,
  val secondaryNotificationBadge: Color,
)

val LocalWorkoutLoggerColorPalette = staticCompositionLocalOf {
  WorkoutLoggerColorPalette(
    gray20 = Color.Unspecified,
    gray60 = Color.Unspecified,
    gray85 = Color.Unspecified,
    white = Color.Unspecified,

    tint = Color.Unspecified,
    green = Color.Unspecified,
    verificationTint = Color.Unspecified,
    error = Color.Unspecified,
    warning = Color.Unspecified,
    bitcoin = Color.Unspecified,
    lending = Color.Unspecified,
    investing = Color.Unspecified,

    behindBackground = Color.Unspecified,
    background = Color.Unspecified,
    secondaryBackground = Color.Unspecified,
    tertiaryBackground = Color.Unspecified,
    placeholderBackground = Color.Unspecified,
    elevatedBackground = Color.Unspecified,
    secondaryElevatedBackground = Color.Unspecified,
    statusBarBackground = Color.Unspecified,

    cardTabBackground = Color.Unspecified,

    label = Color.Unspecified,
    secondaryLabel = Color.Unspecified,
    tertiaryLabel = Color.Unspecified,
    placeholderLabel = Color.Unspecified,
    disabledLabel = Color.Unspecified,
    activeLinkForeground = Color.Unspecified,
    linkForeground = Color.Unspecified,

    cursor = Color.Unspecified,
    clearButtonTint = Color.Unspecified,

    primaryButtonBackground = Color.Unspecified,
    primaryButtonTint = Color.Unspecified,
    primaryButtonTintInverted = Color.Unspecified,
    secondaryButtonBackground = Color.Unspecified,
    secondaryButtonTint = Color.Unspecified,
    tertiaryButtonTint = Color.Unspecified,
    outlineButtonBorder = Color.Unspecified,
    outlineButtonSelectedBorder = Color.Unspecified,
    segmentedControlForeground = Color.Unspecified,
    segmentedControlBackground = Color.Unspecified,

    switchThumbUnchecked = Color.Unspecified,
    switchTrackUnchecked = Color.Unspecified,

    icon = Color.Unspecified,
    secondaryIcon = Color.Unspecified,
    tertiaryIcon = Color.Unspecified,
    placeholderIcon = Color.Unspecified,
    disabledIcon = Color.Unspecified,
    chevron = Color.Unspecified,
    dragHandle = Color.Unspecified,

    hairline = Color.Unspecified,
    outline = Color.Unspecified,
    unselectedPasscodeDot = Color.Unspecified,
    widgetForeground = Color.Unspecified,
    keyboard = Color.Unspecified,

    tabBarShadow = Color.Unspecified,
    bottomTabBarShadow = Color.Unspecified,

    paymentPadBackground = Color.Unspecified,
    paymentPadButtonBackground = Color.Unspecified,
    paymentPadGhostedTextColor = Color.Unspecified,
    paymentPadKeyboard = Color.Unspecified,

    bitcoinPaymentPadBackground = Color.Unspecified,
    bitcoinPaymentPadButtonBackground = Color.Unspecified,

    pageControlUnselected = Color.Unspecified,
    pageControlSelected = Color.Unspecified,
    baselineStroke = Color.Unspecified,
    grayChartStroke = Color.Unspecified,
    scrubbingChartStroke = Color.Unspecified,
    investingCellAccessoryLight = Color.Unspecified,
    investingCellAccessoryDark = Color.Unspecified,
    investingSelectableLabelOutline = Color.Unspecified,
    customOrderBackgroundColor = Color.Unspecified,
    customOrderSelectedLineColor = Color.Unspecified,
    customOrderTooltipBackgroundColor = Color.Unspecified,
    customOrderWidgetButtonBackground = Color.Unspecified,

    scrollBar = Color.Unspecified,
    scrollHint = Color.Unspecified,

    captureLetterbox = Color.Unspecified,
    captureTextColor = Color.Unspecified,
    captureButtonForeground = Color.Unspecified,

    cardCustomizationStroke = Color.Unspecified,
    cardCustomizationStrokeOutsideCard = Color.Unspecified,

    notificationBadge = Color.Unspecified,
    secondaryNotificationBadge = Color.Unspecified,
  )
}
