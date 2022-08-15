@file:OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalMaterialApi::class)

package com.arielwang.workoutlogger.root

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetDefaults
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.SwipeableDefaults
import androidx.compose.material.contentColorFor
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberModalBottomSheetNavigator(
  animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec,
  skipHalfExpanded: Boolean = true,
): BottomSheetNavigator {
  val sheetState = rememberModalBottomSheetState(
    initialValue = ModalBottomSheetValue.Hidden,
    skipHalfExpanded = skipHalfExpanded,
    animationSpec = animationSpec
  )

  return remember(sheetState) {
    BottomSheetNavigator(sheetState = sheetState)
  }
}

/**
 * This function caches the content state of the dismissed `ModalBottomSheet` by retaining
 * it until the dismiss animation is complete to avoid blank content.
 * @param contentState State to be cached until dismiss animation is complete
 * @param bottomSheetState State of the `ModalBottomSheetLayout`
 * @param onHide Callback called when bottom sheet is fully hidden
 */
@Composable
inline fun <reified T : Any> observeBottomSheetContentState(
  contentState: T?,
  bottomSheetState: ModalBottomSheetState =
    rememberModalBottomSheetState(ModalBottomSheetValue.Hidden, skipHalfExpanded = true),
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
  crossinline onShow: () -> Unit = {},
  crossinline onHide: () -> Unit = {},
): T? {
  var cachedState by remember { mutableStateOf(contentState) }

  // Show/hide BottomSheet according to the view state
  LaunchedEffect(contentState) {
    if (contentState == null && bottomSheetState.isVisible) {
      coroutineScope.launch {
        bottomSheetState.hide()
      }
    }
    if (contentState != null && !bottomSheetState.isVisible) {
      cachedState = contentState
      coroutineScope.launch {
        onShow()
        bottomSheetState.show()
      }
    }
  }

  LaunchedEffect(bottomSheetState.currentValue) {
    with(bottomSheetState) {
      if (currentValue == ModalBottomSheetValue.Hidden && cachedState != null) {
        cachedState = null
        onHide()
      }
    }
  }

  return cachedState
}

@Composable
@ExperimentalMaterialApi
inline fun <reified T : Any> ModalBottomSheetLayout(
  sheetContentState: T?,
  crossinline sheetContent: @Composable (T) -> Unit,
  crossinline onShow: () -> Unit = {},
  crossinline onHide: () -> Unit = {},
  modifier: Modifier = Modifier,
  sheetState: ModalBottomSheetState =
    rememberModalBottomSheetState(ModalBottomSheetValue.Hidden, skipHalfExpanded = true),
  sheetShape: Shape = MaterialTheme.shapes.large,
  sheetElevation: Dp = ModalBottomSheetDefaults.Elevation,
  sheetBackgroundColor: Color = MaterialTheme.colors.surface,
  sheetContentColor: Color = contentColorFor(sheetBackgroundColor),
  scrimColor: Color = ModalBottomSheetDefaults.scrimColor,
  noinline content: @Composable () -> Unit,
) {
  val coroutineScope = rememberCoroutineScope()

  // Back button hides BottomSheet if visible
  BackHandler(enabled = sheetState.isVisible) {
    coroutineScope.launch {
      sheetState.hide()
    }
  }

  androidx.compose.material.ModalBottomSheetLayout(
    sheetContent = {
      when (
        val state = observeBottomSheetContentState(
          contentState = sheetContentState,
          coroutineScope = coroutineScope,
          bottomSheetState = sheetState,
          onShow = onShow,
          onHide = onHide,
        )
      ) {
        null -> {
          Spacer(modifier = Modifier.height(1.dp))
        }
        else -> {
          sheetContent(state)
        }
      }
    },
    modifier = modifier,
    sheetState = sheetState,
    sheetShape = sheetShape,
    sheetElevation = sheetElevation,
    sheetBackgroundColor = sheetBackgroundColor,
    sheetContentColor = sheetContentColor,
    scrimColor = scrimColor,
    content = content
  )
}
