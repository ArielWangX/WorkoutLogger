@file:Suppress("MagicNumber")

package com.arielwang.workoutlogger.theme

import androidx.compose.ui.graphics.Color

val black = Color(0xFF000000)
val gray20 = Color(0xFF333333)
val gray40 = Color(0xFF666666)
val gray60 = Color(0xFF999999)
val gray75 = Color(0xFFBFBFBF)
val gray85 = Color(0xFFD9D9D9)
val gray90 = Color(0xFFE5E5E5)
val gray96 = Color(0xFFF4F4F4)
val gray98 = Color(0xFFFAFAFA)
val white = Color(0xFFFFFFFF)

val cobalt = Color(0xFF171819)
val cobalt5 = Color(0xFF232425)
val cobalt10 = Color(0xFF2E2F30)
val cobalt20 = Color(0xFF454647)
val cobalt30 = Color(0xFF5D5D5E)
val cobalt40 = Color(0xFF747576)
val cobalt60 = Color(0xFFA2A3A3)
val behindCobalt = Color(0xFF0C0C0D)

val bgGreen = Color(0xFF00B843)
val lightModeGreen = Color(0xFF00D64F)
val darkModeGreen = Color(0xFF00BD46)
val onGreen = Color(0xFF00C247)

val lightModeAmber = Color(0xFFF46E38)
val darkModeAmber = Color(0xFFE0490C)
val ocean = Color(0xFF3399FF)
val pink = Color(0xFFFB60C4)
val purple = Color(0xFFB141FF)
val red = Color(0xFFF84049)
val royal = Color(0xFF3F6AFF)
val scarlet = Color(0xFFFF4A4A)
val sky = Color(0xFF00D4FF)
val steel = Color(0xFF343B42)
val sunshine = Color(0xFFFADA3D)
val turquoise = Color(0xFF41EBC1)
val bitcoin = Color(0xFF00D4FF)
val bitcoinOrange = Color(0xFFF78A2B)
val bgBitcoin = Color(0xFF00C5F0)
val stableCoinBlue = Color(0xFF2368C0)
val investingLight = Color(0xFF9013FE)
val investingDark = Color(0xFFB141FF)
val bottomTabShadowLight = Color(0x0F000000)
val bottomTabShadowDark = Color(0x24000000)

internal val workoutLoggerLightColorPalette = WorkoutLoggerColorPalette(
  gray20 = gray20,
  gray60 = gray60,
  gray85 = gray85,
  white = white,

  tint = lightModeGreen,
  green = lightModeGreen,
  verificationTint = ocean,
  error = red,
  warning = lightModeAmber,
  bitcoin = bitcoin,
  lending = ocean,
  investing = investingLight,

  behindBackground = gray96,
  background = white,
  secondaryBackground = gray98,
  tertiaryBackground = white,
  placeholderBackground = gray85,
  elevatedBackground = white,
  secondaryElevatedBackground = gray98,
  statusBarBackground = white,

  cardTabBackground = gray98,

  label = gray20,
  secondaryLabel = gray40,
  tertiaryLabel = gray60,
  placeholderLabel = gray75,
  disabledLabel = gray75,
  activeLinkForeground = gray20,
  linkForeground = gray75,

  cursor = lightModeGreen,
  clearButtonTint = gray75,

  primaryButtonBackground = lightModeGreen,
  primaryButtonTint = white,
  primaryButtonTintInverted = gray20,
  secondaryButtonBackground = gray96,
  secondaryButtonTint = gray20,
  tertiaryButtonTint = lightModeGreen,
  outlineButtonBorder = gray85,
  outlineButtonSelectedBorder = lightModeGreen,
  segmentedControlForeground = white,
  segmentedControlBackground = gray96,

  switchThumbUnchecked = white,
  switchTrackUnchecked = gray75,

  icon = gray20,
  secondaryIcon = gray40,
  tertiaryIcon = gray60,
  placeholderIcon = gray85,
  disabledIcon = gray85,
  chevron = gray85,
  dragHandle = gray85,

  hairline = gray90,
  outline = gray85,
  unselectedPasscodeDot = gray90,
  widgetForeground = black,
  keyboard = gray40,

  tabBarShadow = black,
  bottomTabBarShadow = bottomTabShadowLight,

  paymentPadBackground = bgGreen,
  paymentPadButtonBackground = onGreen,
  paymentPadGhostedTextColor = onGreen,
  paymentPadKeyboard = white,

  bitcoinPaymentPadBackground = bgBitcoin,
  bitcoinPaymentPadButtonBackground = bitcoin,

  pageControlUnselected = gray75,
  pageControlSelected = gray20,
  baselineStroke = gray90,
  grayChartStroke = gray75,
  scrubbingChartStroke = gray85,
  investingCellAccessoryLight = gray96,
  investingCellAccessoryDark = gray85,
  investingSelectableLabelOutline = gray96,
  customOrderBackgroundColor = black,
  customOrderSelectedLineColor = gray20,
  customOrderTooltipBackgroundColor = white,
  customOrderWidgetButtonBackground = gray96,

  scrollBar = gray60,
  scrollHint = gray85,

  captureLetterbox = black,
  captureTextColor = white,
  captureButtonForeground = white,

  cardCustomizationStroke = black,
  cardCustomizationStrokeOutsideCard = gray85,

  notificationBadge = scarlet,
  secondaryNotificationBadge = white,
)

internal val workoutLoggerDarkColorPalette = WorkoutLoggerColorPalette(
  gray20 = white,
  gray60 = white,
  gray85 = gray85,
  white = black,

  tint = darkModeGreen,
  green = darkModeGreen,
  verificationTint = ocean,
  error = red,
  warning = darkModeAmber,
  bitcoin = bitcoin,
  lending = ocean,
  investing = investingDark,

  behindBackground = behindCobalt,
  background = cobalt,
  secondaryBackground = cobalt5,
  tertiaryBackground = cobalt10,
  placeholderBackground = cobalt20,
  elevatedBackground = cobalt5,
  secondaryElevatedBackground = cobalt10,
  statusBarBackground = cobalt,

  cardTabBackground = cobalt,

  label = white,
  secondaryLabel = cobalt60,
  tertiaryLabel = cobalt40,
  placeholderLabel = cobalt40,
  disabledLabel = cobalt30,
  activeLinkForeground = white,
  linkForeground = cobalt30,

  cursor = darkModeGreen,
  clearButtonTint = cobalt20,

  primaryButtonBackground = darkModeGreen,
  primaryButtonTint = white,
  primaryButtonTintInverted = cobalt,
  secondaryButtonBackground = cobalt20,
  secondaryButtonTint = white,
  tertiaryButtonTint = darkModeGreen,
  outlineButtonBorder = cobalt20,
  outlineButtonSelectedBorder = darkModeGreen,
  segmentedControlForeground = cobalt20,
  segmentedControlBackground = cobalt5,

  switchThumbUnchecked = white,
  switchTrackUnchecked = cobalt60,

  icon = white,
  secondaryIcon = cobalt60,
  tertiaryIcon = cobalt40,
  placeholderIcon = cobalt20,
  disabledIcon = cobalt20,
  chevron = cobalt40,
  dragHandle = cobalt40,

  hairline = behindCobalt,
  outline = cobalt20,
  unselectedPasscodeDot = cobalt5,
  widgetForeground = white,
  keyboard = white,

  tabBarShadow = black,
  bottomTabBarShadow = bottomTabShadowDark,

  paymentPadBackground = cobalt,
  paymentPadButtonBackground = cobalt20,
  paymentPadGhostedTextColor = gray20,
  paymentPadKeyboard = white,

  bitcoinPaymentPadBackground = cobalt,
  bitcoinPaymentPadButtonBackground = cobalt20,

  pageControlUnselected = cobalt40,
  pageControlSelected = white,
  baselineStroke = cobalt20,
  grayChartStroke = cobalt40,
  scrubbingChartStroke = cobalt20,
  investingCellAccessoryLight = cobalt40,
  investingCellAccessoryDark = cobalt20,
  investingSelectableLabelOutline = cobalt20,
  customOrderBackgroundColor = white,
  customOrderSelectedLineColor = white,
  customOrderTooltipBackgroundColor = gray20,
  customOrderWidgetButtonBackground = cobalt,

  scrollBar = cobalt40,
  scrollHint = cobalt10,

  captureLetterbox = black,
  captureTextColor = white,
  captureButtonForeground = white,

  cardCustomizationStroke = white,
  cardCustomizationStrokeOutsideCard = gray20,

  notificationBadge = scarlet,
  secondaryNotificationBadge = scarlet,
)
