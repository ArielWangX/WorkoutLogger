package com.arielwang.workoutlogger.features.landing.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arielwang.workoutlogger.R
import com.arielwang.workoutlogger.theme.WorkoutLoggerTheme

@Composable
fun LandingScreen(
  uiState: LandingView.State = LandingView.State(),
  onAction: (LandingView.Action) -> Unit = {}
) {
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {

    Text(
      text = stringResource(id = R.string.LandingScreen_title, uiState.currentTime),
      style = MaterialTheme.typography.subtitle2
    )

    Button(
      modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
      onClick = { onAction(LandingView.Action.GoToNextPage) }) {
      Text(text = stringResource(id = R.string.LandingScreen_buttonContent))
    }

  }
}