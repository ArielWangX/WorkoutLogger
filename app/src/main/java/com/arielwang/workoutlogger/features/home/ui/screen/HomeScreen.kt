package com.arielwang.workoutlogger.features.home.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.arielwang.workoutlogger.R
import com.arielwang.workoutlogger.features.component.ScreenButton


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

        HomeScreenContent(Modifier.constrainAs(text) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        )

        HomeScreenButton(
            buttonConstraintLayout = Modifier.constrainAs(button) {
                bottom.linkTo(parent.bottom)
            },
            onAction = onAction
        )

    }
}

@Composable
fun HomeScreenContent(textConstraintLayout: Modifier) {

    Text(
        text = stringResource(id = R.string.HomeScreen_title),
        modifier = textConstraintLayout,
        style = MaterialTheme.typography.subtitle2,
        color = MaterialTheme.colors.onSecondary
    )
}

@Composable
fun HomeScreenButton(
    buttonConstraintLayout: Modifier,
    onAction: (HomeView.Action) -> Unit = {}
) {
    ScreenButton(
        buttonConstraintLayout = buttonConstraintLayout,
        buttonText = R.string.HomeScreen_buttonContent,
        onAction = { onAction(HomeView.Action.GoToNextPage) }
    )
}


