package com.arielwang.workoutlogger.features.track.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.widget.ConstraintLayout
import com.arielwang.workoutlogger.R
import com.arielwang.workoutlogger.features.component.ScreenButton

@Composable
fun TrackScreen(
    uiState: TrackView.State = TrackView.State()
 //   onAction: (TrackView.Action) -> Unit
) {

}

@Composable
fun TrackScreenButton(
    buttonConstraintLayout: Modifier,
    onAction: (TrackView.Action) -> Unit = {}
) {
    ScreenButton(
        buttonConstraintLayout = buttonConstraintLayout,
        buttonText = R.string.TrackScreen_buttonContent,
        onAction = { onAction(TrackView.Action.GoToNextPage) }
    )
}