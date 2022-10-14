package com.arielwang.workoutlogger.features.track.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.arielwang.workoutlogger.R
import com.arielwang.workoutlogger.features.component.PrimaryButton

@Composable
fun TrackScreen(
    uiState: TrackView.State = TrackView.State(),
    onAction: (TrackView.Action) -> Unit
) {
    PrimaryButton(
        modifier = Modifier,
        buttonText = stringResource(id = R.string.TrackScreen_buttonContent),
        onAction = { onAction(TrackView.Action.GoToNextPage) }
    )
}
