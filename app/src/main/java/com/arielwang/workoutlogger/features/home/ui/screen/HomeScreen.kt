package com.arielwang.workoutlogger.features.home.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.arielwang.workoutlogger.R


@Composable
fun HomeScreen() {
    HomeScreenConstraintLayout()
}

@Composable
fun HomeScreenConstraintLayout() {
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

        HomeScreenButton(Modifier.constrainAs(button) {
            bottom.linkTo(parent.bottom)
        }
        )

    }
}

@Composable
fun HomeScreenContent(textConstraintLayout: Modifier) {

    Text(
        text = stringResource(id = R.string.HomeScreen_title),
        modifier = textConstraintLayout,
        style = MaterialTheme.typography.subtitle2,
        color = MaterialTheme.colors.secondary
    )
}

@Composable
fun HomeScreenButton(buttonConstrintLayout: Modifier) {

    Button(
        modifier = buttonConstrintLayout
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary
        ),
        onClick = { HomeView.Action.GoToNextPage }) {
        Text(
            text = stringResource(id = R.string.HomeScreen_buttonContent),
            modifier = Modifier.padding(vertical = 10.dp)
        )
    }
}

