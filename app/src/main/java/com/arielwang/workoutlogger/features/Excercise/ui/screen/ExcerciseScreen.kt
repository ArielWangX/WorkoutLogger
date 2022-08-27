package com.arielwang.workoutlogger.features.Excercise.ui.screen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.arielwang.workoutlogger.R

@Composable
fun ExcerciseScreen() {

}

@Composable
fun ExcerciseCard(
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Surface(modifier = Modifier) {
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
fun ExcerciseScreenButton() {
    Button(onClick = {ExcerciseView.Action.GoToNextPage},
        modifier = Modifier) {
        Text(text = stringResource(id = R.string.ExcerciseScreen_buttonContent))
    }
}

private val excerciseCardData = listOf(
    R.string.ExcerciseCard_abs,
    R.string.ExcerciseCard_back,
    R.string.ExcerciseCard_biceps,
    R.string.ExcerciseCard_cardio,
    R.string.ExcerciseCard_chest
)