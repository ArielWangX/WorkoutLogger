package com.arielwang.workoutlogger.features.addexerciseflow.exercise.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.arielwang.workoutlogger.R
import com.arielwang.workoutlogger.features.component.PrimaryButton
import com.arielwang.workoutlogger.features.component.ScaffoldWithTopBar

@Composable
fun ExerciseScreen(
    uiState: ExerciseView.State = ExerciseView.State(),
    onAction: (ExerciseView.Action) -> Unit
) {
    ScaffoldWithTopBar(
        title = R.string.ExerciseScreen_topbarTitle,
        iconContentDescription = R.string.ExerciseScreen_topbarIconButtonContentDescription,
        onAction = { onAction(ExerciseView.Action.GoBackToPreviousPage)},
        modifier = Modifier.systemBarsPadding()
    ) {
        ExerciseContentConstraintLayout(uiState = uiState, onAction = onAction)
    }
}

@Composable
fun ExerciseContentConstraintLayout(
    uiState: ExerciseView.State,
    onAction: (ExerciseView.Action) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        val (button, cardListSection) = createRefs()

        ExerciseCardList(
            uiState = uiState,
            onAction = onAction,
            modifier = Modifier.constrainAs(cardListSection) {
                top.linkTo(parent.top)
                bottom.linkTo(button.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        PrimaryButton(
            modifier = Modifier.constrainAs(button) {
                bottom.linkTo(parent.bottom)
            },
            buttonText = stringResource(id = R.string.ExerciseScreen_buttonContent),
            onAction = { onAction(ExerciseView.Action.GoToNextPage)}
        )
    }
}

@Composable
fun ExerciseCardList(
    uiState: ExerciseView.State,
    onAction: (ExerciseView.Action) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {Spacer(modifier = Modifier.height(32.dp))}

        uiState.cardList.forEach {
            item { ExerciseCard(text = it.text, isSelected = it.isSelected, onAction = onAction) }
        }

        item {Spacer(modifier = Modifier.height(24.dp))}
    }
}

@Composable
fun ExerciseCard(
    text: String,
    isSelected: Boolean,
    onAction: (ExerciseView.Action) -> Unit
) {
    Card(
        border = BorderStroke(
            2.dp,
            if (isSelected)
                MaterialTheme.colors.secondary
            else
                MaterialTheme.colors.primaryVariant
        ),
        backgroundColor = MaterialTheme.colors.onPrimary,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clickable { onAction(ExerciseView.Action.OnCardClicked(text)) }
        ) {
            Text(
                text = text,
                color = MaterialTheme.colors.onSecondary,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    tint = MaterialTheme.colors.secondary,
                    contentDescription = null
                )
            }
        }
    }
}

