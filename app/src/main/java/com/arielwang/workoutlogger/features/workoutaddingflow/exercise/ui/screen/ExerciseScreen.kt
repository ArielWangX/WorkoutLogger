package com.arielwang.workoutlogger.features.workoutaddingflow.exercise.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.arielwang.workoutlogger.R
import com.arielwang.workoutlogger.features.component.PrimaryButton
import com.arielwang.workoutlogger.features.component.WorkoutLoggerScaffold

@Composable
fun ExerciseScreen(
    uiState: ExerciseView.State = ExerciseView.State(),
    onAction: (ExerciseView.Action) -> Unit
) {
    WorkoutLoggerScaffold(
        title = R.string.ExerciseScreen_topbarTitle,
        iconContentDescription = R.string.ExerciseScreen_topbarBackIconButton,
        onAction = { onAction(ExerciseView.Action.GoBackToPreviousPage) },
        actions = {
            IconButton(
                onClick = { onAction(ExerciseView.Action.AddExerciseCard) },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.ExerciseScreen_topbarAddIconButton)
                )
            }
        }
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
            .systemBarsPadding()
            .padding(horizontal = 16.dp)
    ) {
        val (button, cardListSection, text) = createRefs()

        if (uiState.cardList.isEmpty()) {
            ExerciseScreenDefaultText(
                modifier = Modifier.constrainAs(text) {
                    top.linkTo(parent.top)
                    bottom.linkTo(button.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        } else {
            ExerciseCardList(
                modifier = Modifier.constrainAs(cardListSection) {
                    linkTo(parent.top, button.top, bias = 0f)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                },
                uiState = uiState,
                onAction = onAction
            )
        }

        PrimaryButton(
            modifier = Modifier.constrainAs(button) {
                bottom.linkTo(parent.bottom)
            },
            buttonText = stringResource(id = R.string.ExerciseScreen_buttonContent),
            onAction = { onAction(ExerciseView.Action.GoToNextPage) }
        )
    }
}

@Composable
fun ExerciseScreenDefaultText(modifier: Modifier) {
    Text(
        text = stringResource(id = R.string.ExerciseScreen_defaultText),
        modifier = modifier,
        style = MaterialTheme.typography.subtitle2,
        color = MaterialTheme.colors.onSecondary
    )
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
        uiState.cardList.forEach {
            item {
                ExerciseCard(
                    text = it.text,
                    isSelected = it.isSelected,
                    onAction = onAction
                )
            }
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }
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
                MaterialTheme.colors.primaryVariant
            else
                MaterialTheme.colors.surface
        ),
        backgroundColor = MaterialTheme.colors.surface,
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
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    tint = MaterialTheme.colors.primaryVariant,
                    contentDescription = null
                )
            }
        }
    }
}

