package com.arielwang.workoutlogger.features.Excercise.ui.screen

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arielwang.workoutlogger.R
import com.arielwang.workoutlogger.features.home.ui.screen.HomeView
import kotlin.math.abs

@Composable
fun ExcerciseCard(
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    var checked by remember { mutableStateOf(false) }

    Card(
        border = BorderStroke(2.dp, Color.LightGray),
        backgroundColor = MaterialTheme.colors.onPrimary,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(text),
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            
            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = { checked = !checked }) {
                if (checked) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null
                    )
                }
            }
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
        onClick = { onAction(ExcerciseView.Action.GoToNextPage) }
    ) {
        Text(
            text = stringResource(id = R.string.ExcerciseScreen_buttonContent),
            modifier = Modifier.padding(vertical = 10.dp)
        )
    }
}

@Composable
fun ExcerciseScreen(
    uiState: ExcerciseView.State = ExcerciseView.State(),
    onAction: (ExcerciseView.Action) -> Unit = {}
) {
    ExcerciseCard(text = R.string.ExcerciseCard_back)
}

