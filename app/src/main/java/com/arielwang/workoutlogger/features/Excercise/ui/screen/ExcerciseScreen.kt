package com.arielwang.workoutlogger.features.Excercise.ui.screen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arielwang.workoutlogger.R
import com.arielwang.workoutlogger.features.home.ui.screen.HomeView

@Composable
fun ExcerciseCard(
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
    ) {
        Row {
            Text(text = stringResource(text))
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null
            )
        }
    }
}

@Composable
fun ExcerciseCardCollection(modifier: Modifier = Modifier) {
    LazyColumn(modifier = Modifier) {
        items(excerciseCardData) { item ->
            ExcerciseCard(text = item)
        }
    }
}

@Composable
fun ExcerciseScreenButton(
    buttonConstraintLayout: Modifier,
    onAction: (ExcerciseView.Action) -> Unit = {}
) {
    Button(
        modifier = buttonConstraintLayout
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary
        ),
        onClick = {onAction(ExcerciseView.Action.GoToNextPage)}
        ) {
        Text(
            text = stringResource(id = R.string.ExcerciseScreen_buttonContent),
            modifier = Modifier.padding(vertical = 10.dp)
        )
    }
}

@Composable
fun ExcerciseScreen(
    uiState: HomeView.State = HomeView.State(),
    onAction: (HomeView.Action) -> Unit = {}
) {
}

