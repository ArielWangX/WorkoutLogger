package com.arielwang.workoutlogger.core.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner

@Composable
inline fun <reified VM : ViewModel> screenViewModel(
  viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
    "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
  },
  lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
): VM {
  return hiltViewModel<VM>(viewModelStoreOwner).apply {
    if (this is ScreenLifecycleObserver) {
      ObserveScreenLifecycle(this, lifecycleOwner)
    }
  }
}

/**
 * Add an observer for the Screen Composable's lifecycle.
 * @param observer Observer for the lifecycle callbacks, usually a `ViewModel`
 * @param lifecycleOwner `NavBackStackEntry` for the screen, defaults to `LocalLifecycleOwner`
 */
@Composable
fun ObserveScreenLifecycle(
  observer: ScreenLifecycleObserver,
  lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
  with(lifecycleOwner) {
    DisposableEffect(this, observer) {
      observer.onLaunch()
      LifecycleEventObserver { _, event ->
        when (event) {
          Lifecycle.Event.ON_START -> observer.onShow()
          Lifecycle.Event.ON_RESUME -> observer.onActive()
          Lifecycle.Event.ON_PAUSE -> observer.onInactive()
          Lifecycle.Event.ON_STOP -> observer.onHide()
          else -> {}
        }
      }.let { eventObserver ->
        lifecycle.addObserver(eventObserver)
        onDispose {
          lifecycle.removeObserver(eventObserver)
          observer.onDispose()
        }
      }
    }
  }
}

interface ScreenLifecycleObserver {

  /**
   * Called when Screen Composable is launched
   * - when navigating to the screen
   * - when returning from a child screen
   *
   * Use instead of `ViewModel.init()` to set up the ViewModel
   */
  fun onLaunch() {}

  /**
   * Called when Screen Composable is shown
   * - when navigating to the screen
   * - when returning from a child screen
   *
   * Use when an action should be triggered every time a screen is shown
   */
  fun onShow() {}

  /**
   * Called when Screen Composable becomes the active screen
   * - when navigating to the screen
   * - when returning from a child screen
   * - when returning from a modal bottom sheet
   */
  fun onActive() {}

  /**
   * Called when Screen Composable is
   * - when popping the screen from backstack
   * - when navigating to another screen
   * - when navigating to modal bottom sheet
   */
  fun onInactive() {}

  /**
   * Called when Screen Composable is not visible
   * - when popping the screen from backstack
   * - when navigating to another screen
   */
  fun onHide() {}

  /**
   * Called when Screen Composable is disposed
   * - when popping the screen from backstack
   */
  fun onDispose() {}
}
