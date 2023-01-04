package com.arielwang.workoutlogger.features.home.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            state = uiState
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
fun HomeScreenContent(modifier: Modifier, state: HomeView.State) {

    if (state.exercises.isEmpty()) {
        Text(
            text = stringResource(id = R.string.HomeScreen_title),
            modifier = modifier,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.onSecondary
        )
    } else {
        LazyColumn {
            state.exercises.forEach {
                item {
                    Text(
                        text = it.type,
                        style = MaterialTheme.typography.subtitle2,
                        color = MaterialTheme.colors.onSecondary
                    )
                }
            }
        }
    }
}



