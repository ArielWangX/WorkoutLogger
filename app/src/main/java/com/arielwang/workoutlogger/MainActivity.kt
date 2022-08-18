package com.arielwang.workoutlogger

import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.arielwang.workoutlogger.core.ui.compositionlocal.LocalWindowSize
import com.arielwang.workoutlogger.core.ui.compositionlocal.rememberWindowSizeClass
import com.arielwang.workoutlogger.navigate.Navigator
import com.arielwang.workoutlogger.navigate.setup
import com.arielwang.workoutlogger.root.RootScreen
import com.arielwang.workoutlogger.root.RootViewModel
import com.arielwang.workoutlogger.root.rememberModalBottomSheetNavigator
import com.arielwang.workoutlogger.theme.WorkoutLoggerTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@OptIn(ExperimentalMaterialNavigationApi::class)
@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {

  @Inject
  lateinit var navigator: Navigator

  private val rootViewModel by viewModels<RootViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    WindowCompat.setDecorFitsSystemWindows(window, false)

    val splashScreen = installSplashScreen()
    splashScreen.setOnExitAnimationListener {
      try {
        val iconAnimation = it.iconView.animate()
          .scaleX(0f)
          .scaleY(0f)
          .setDuration(500L)
          .setInterpolator(AccelerateInterpolator())
        val splashAnimator = it.view.animate()
          .alpha(0f)
          .setDuration(500L)
          .withEndAction {
            it.remove()
          }
        iconAnimation.start()
        splashAnimator.start()
      } catch (e: Throwable) {
        // Restart process will cause view null exception when targeting SDK 33 and
        // running on Android 13. Workaround is to catch exceptions in Exit Animation and
        // finish immediately
        it.remove()
      }
    }

    setContent {
      val systemUiController = rememberSystemUiController()
      val useDarkIcons = !isSystemInDarkTheme()

      val windowSize = rememberWindowSizeClass()

      CompositionLocalProvider(LocalWindowSize provides windowSize) {

        SideEffect {
          // need to manually set windowBackground as postSplashScreenTheme is not properly
          // applied on some devices
          window?.setBackgroundDrawableResource(R.color.background)
          systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
          )
        }

        val bottomSheetNavigator = rememberModalBottomSheetNavigator()
        val navController = rememberNavController(bottomSheetNavigator)
        LaunchedEffect(navigator) {
          navigator.setup(this, navController)
        }

        WorkoutLoggerTheme {
          ProvideWindowInsets {
            RootScreen(
              bottomSheetNavigator,
              navController,
            )
          }
        }
      }
    }
  }
}
