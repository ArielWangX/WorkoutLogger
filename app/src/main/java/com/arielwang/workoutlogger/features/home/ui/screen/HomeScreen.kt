package com.arielwang.workoutlogger.features.home.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.arielwang.workoutlogger.R
import com.arielwang.workoutlogger.features.component.PrimaryButton


@Composable
fun HomeScreen(
    uiState: HomeView.State = HomeView.State(),
    onAction: (HomeView.Action) -> Unit = {}
) {
    HomeScreenConstraintLayout(uiState, onAction)
}

@Composable
fun HomeScreenConstraintLayout(
    uiState: HomeView.State = HomeView.State(),
    onAction: (HomeView.Action) -> Unit = {}
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 16.dp)
    ) {
        val (button, text) = createRefs()

        HomeScreenContent(
            modifier = Modifier.constrainAs(text) {
                top.linkTo(parent.top)
                bottom.linkTo(button.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            uiState = uiState
        )

        PrimaryButton(
            modifier = Modifier.constrainAs(button) {
                bottom.linkTo(parent.bottom)
            },
            buttonText = stringResource(id = R.string.HomeScreen_buttonContent),
            onAction = { onAction(HomeView.Action.GoToNextPage) }
        )
    }
}

@Composable
fun HomeScreenContent(
    modifier: Modifier,
    uiState: HomeView.State
) {
    if (uiState.workout.isEmpty()) {
        Text(
            text = stringResource(id = R.string.HomeScreen_defaultText),
            modifier = modifier,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.onSecondary
        )
    } else {
        DisplayExerciseCardList(uiState = uiState)
    }
}

@Composable
fun DisplayExerciseCardList(
    uiState: HomeView.State
) {
    LazyColumn {
        item {Spacer(modifier = Modifier.height(32.dp))}

        uiState.workout.forEach {
            item{
                DisplayExerciseCard(text = it)
            }
        }

        item {Spacer(modifier = Modifier.height(32.dp))}
    }
}


@Composable
fun DisplayExerciseCard(
    text: String
) {
    Card(
        backgroundColor = MaterialTheme.colors.onPrimary,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.onSecondary,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}


