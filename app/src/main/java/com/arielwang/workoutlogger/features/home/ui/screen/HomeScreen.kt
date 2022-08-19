package com.arielwang.workoutlogger.features.home.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arielwang.workoutlogger.R


@Composable
fun HomeScreen(
    uiState: HomeView.State = HomeView.State(),
    onAction: (HomeView.Action) -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.HomeScreen_title, uiState.workoutForToday),
            style = MaterialTheme.typography.titleSmall
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            shape = RoundedCornerShape(2.dp),
            colors = ,
            onClick = { onAction(HomeView.Action.GoToNextPage) }) {
            Text(text = stringResource(id = R.string.HomeScreen_buttonContent))
        }
    }
}